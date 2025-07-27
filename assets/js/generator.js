const MAP = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ0123456789ABCDEFGHJKLMNPQRSTUV";
const INIT_VECTOR = new Uint8Array([
    173, 44, 134, 157, 248, 202, 171, 85, 17, 42,
    30, 130, 153, 108, 118, 107
]);

function encrypt(data, key) {
    // Create Twofish instance - the library expects the IV to be passed to the main function
    const tf = twofish(Array.from(key));
    
    // Use the encrypt method from the returned object
    const encrypted = tf.encrypt(Array.from(key), Array.from(data));
    
    // XOR with key (first 16 bytes)
    const result = new Uint8Array(16);
    for (let i = 0; i < 16; i++) {
        result[i] = key[i] ^ encrypted[i];
    }
    
    return result;
}

function generateActivationCode(mapCodeBytes, vinCode) {
    try {
        if (vinCode.length < 17) {
            throw new Error("VIN too short");
        }

        // Extract map code (first 8 bytes + null terminator)
        const mapCode = new Uint8Array(9);
        mapCode.set(mapCodeBytes.slice(0, 8));
        mapCode[8] = 0;

        // Create VIN code from characters 8-16 of VIN
        const vinCodeBytes = new Uint8Array(12);
        let sum = 0;
        for (let i = 0; i < 9; i++) {
            const b = vinCode.charCodeAt(i + 8);
            vinCodeBytes[i] = b;
            sum += b;
        }

        // Calculate checksum
        sum = ((sum ^ 0xFFFFFFFF) + 1) & 0xFF;
        const checksumStr = sum.toString().padStart(2, '0');
        vinCodeBytes[10] = checksumStr.charCodeAt(0);
        vinCodeBytes[11] = checksumStr.charCodeAt(1);

        console.log("map_code=" + new TextDecoder().decode(mapCode.slice(0, 8)));
        console.log("vin_code=" + new TextDecoder().decode(vinCodeBytes.slice(0, 9)));

        // Create combined data array (33 bytes)
        const combinedData = new Uint8Array(33);
        
        // First part: VIN code bytes duplicated
        for (let i = 0; i < 9; i++) {
            combinedData[2 * i] = vinCodeBytes[i];
            combinedData[2 * i + 1] = vinCodeBytes[i];
        }

        // Second part: Map code bytes duplicated
        for (let i = 0; i < 7; i++) {
            combinedData[18 + 2 * i] = mapCode[i];
            combinedData[18 + 2 * i + 1] = mapCode[i];
        }
        combinedData[31] = mapCode[7];
        combinedData[32] = 0;

        // First encryption round
        let iv = new Uint8Array(INIT_VECTOR);
        let encrypted = encrypt(combinedData.slice(0, 16), iv);

        // Update IV and prepare second block
        iv = encrypted;
        const secondBlock = combinedData.slice(16, 32);

        // Second encryption round
        encrypted = encrypt(secondBlock, iv);

        // Generate activation code
        const activationBytes = new Uint8Array(17);
        for (let i = 0; i < 16; i++) {
            activationBytes[i] = MAP.charCodeAt((encrypted[i] >> 1) & 0x3F);
        }
        activationBytes[16] = 0;

        const activationCode = new TextDecoder().decode(activationBytes.slice(0, 16));
        console.log("Activation key: " + activationCode);
        
        return activationCode;
    } catch (error) {
        throw new Error("Generation failed: " + error.message);
    }
}

document.getElementById('keygenForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const vinInput = document.getElementById('vinInput').value.trim();
    const fileInput = document.getElementById('fileInput');
    
    // Hide previous results
    document.getElementById('result').style.display = 'none';
    document.getElementById('error').style.display = 'none';
    
    try {
        // Validate inputs
        if (!vinInput || vinInput.length < 17) {
            throw new Error("VIN must be at least 17 characters long");
        }
        
        if (!fileInput.files[0]) {
            throw new Error("Please select a CCT.DAT.inf file");
        }
        
        // Read file
        const file = fileInput.files[0];
        const arrayBuffer = await file.arrayBuffer();
        const fileBytes = new Uint8Array(arrayBuffer);
        
        if (fileBytes.length < 8) {
            throw new Error("File is too small (need at least 8 bytes)");
        }
        
        // Generate activation code
        const activationCode = generateActivationCode(fileBytes, vinInput);
        
        // Show result
        document.getElementById('activationCode').value = activationCode;
        document.getElementById('result').style.display = 'block';
        
    } catch (error) {
        document.getElementById('errorMessage').textContent = error.message;
        document.getElementById('error').style.display = 'block';
    }
});

// Copy to clipboard functionality
document.getElementById('copyBtn').addEventListener('click', function() {
    const activationCode = document.getElementById('activationCode');
    activationCode.select();
    document.execCommand('copy');
    
    // Visual feedback
    const btn = this;
    const originalHTML = btn.innerHTML;
    btn.innerHTML = '<i class="bi bi-check"></i>';
    btn.classList.add('btn-success');
    btn.classList.remove('btn-outline-secondary');
    
    setTimeout(() => {
        btn.innerHTML = originalHTML;
        btn.classList.remove('btn-success');
        btn.classList.add('btn-outline-secondary');
    }, 2000);
});

// VIN input validation
document.getElementById('vinInput').addEventListener('input', function(e) {
    const value = e.target.value.toUpperCase();
    e.target.value = value;
    
    if (value.length === 17) {
        e.target.classList.add('is-valid');
        e.target.classList.remove('is-invalid');
    } else if (value.length > 0) {
        e.target.classList.add('is-invalid');
        e.target.classList.remove('is-valid');
    } else {
        e.target.classList.remove('is-valid', 'is-invalid');
    }
});
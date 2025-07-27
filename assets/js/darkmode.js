// Dark mode functionality
const darkModeSwitch = document.getElementById('darkModeSwitch');
const darkModeIcon = document.getElementById('darkModeIcon');
const body = document.body;
const card = document.querySelector('.card');
const html = document.documentElement;

// Media query to detect system dark mode preference
const darkModeMediaQuery = window.matchMedia('(prefers-color-scheme: dark)');

// Check for saved dark mode preference
const savedDarkMode = localStorage.getItem('darkMode');
let isDarkMode;

if (savedDarkMode !== null) {
    // User has manually set preference, use that
    isDarkMode = savedDarkMode === 'true';
} else {
    // No manual preference, use system preference
    isDarkMode = darkModeMediaQuery.matches;
}

// Set initial state
if (isDarkMode) {
    enableDarkMode();
    darkModeSwitch.checked = true;
} else {
    disableDarkMode();
    darkModeSwitch.checked = false;
}

// Listen for manual toggle
darkModeSwitch.addEventListener('change', function() {
    if (this.checked) {
        enableDarkMode();
        localStorage.setItem('darkMode', 'true');
    } else {
        disableDarkMode();
        localStorage.setItem('darkMode', 'false');
    }
});

// Listen for system dark mode changes (only if no manual preference is set)
darkModeMediaQuery.addEventListener('change', function(e) {
    // Only respond to system changes if user hasn't manually set a preference
    if (localStorage.getItem('darkMode') === null) {
        if (e.matches) {
            enableDarkMode();
            darkModeSwitch.checked = true;
        } else {
            disableDarkMode();
            darkModeSwitch.checked = false;
        }
    }
});

function enableDarkMode() {
    body.classList.remove('bg-light');
    body.classList.add('bg-dark', 'text-light');
    card.classList.add('bg-dark', 'text-light');
    card.style.border = '1px solid #495057';
    darkModeIcon.classList.remove('bi-sun-fill');
    darkModeIcon.classList.add('bi-moon-fill');
    html.setAttribute('data-bs-theme', 'dark');
    
    // Update form controls for dark mode
    const inputs = document.querySelectorAll('.form-control');
    inputs.forEach(input => {
        input.classList.add('bg-dark', 'text-light', 'border-secondary');
        input.style.borderColor = '#495057';
    });
    
    // Update buttons for dark mode
    const copyBtn = document.getElementById('copyBtn');
    if (copyBtn) {
        copyBtn.classList.remove('btn-outline-secondary');
        copyBtn.classList.add('btn-outline-light');
    }
}

function disableDarkMode() {
    body.classList.remove('bg-dark', 'text-light');
    body.classList.add('bg-light');
    card.classList.remove('bg-dark', 'text-light');
    card.style.border = '';
    darkModeIcon.classList.remove('bi-moon-fill');
    darkModeIcon.classList.add('bi-sun-fill');
    html.removeAttribute('data-bs-theme');
    
    // Reset form controls
    const inputs = document.querySelectorAll('.form-control');
    inputs.forEach(input => {
        input.classList.remove('bg-dark', 'text-light', 'border-secondary');
        input.style.borderColor = '';
    });
    
    // Reset buttons
    const copyBtn = document.getElementById('copyBtn');
    if (copyBtn) {
        copyBtn.classList.remove('btn-outline-light');
        copyBtn.classList.add('btn-outline-secondary');
    }
}
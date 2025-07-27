# 🌍 SMEG Map Activation Key Generator

A modern, web-based tool for generating activation keys for SMEG navigation systems in Peugeot, Citroën, and DS vehicles.

## ✨ Features

- 🎨 **Modern UI** with Bootstrap 5 styling
- 🌙 **Dark/Light Mode** with system preference detection
- 📱 **Responsive Design** for desktop and mobile
- 🔐 **Secure Encryption** using Twofish algorithm
- 📋 **Copy to Clipboard** functionality
- ⚡ **Client-side Processing** - no data leaves your browser

## 🚗 Supported Systems

This generator works with various SMEG navigation systems:

- **SMEG systems**: `/SMEG_UPG/DATA/CCT.DAT.inf`
- **SMEG + systems**: `/SMEG_PLUS_UPG/DATA/CCT.DAT.inf`
- **SMEG iv2 systems**: `/SMEG_IV2_UPG/DATA/CCT.DAT.inf`
- **RT6 systems**: `/CCT.DAT.inf` (root directory)

## 🛠️ How to Use

1. **Find your VIN**: Locate your vehicle's 17-character VIN number
2. **Get CCT.DAT.inf**: Extract the correct CCT.DAT.inf file from your map update
3. **Generate Key**: Upload the file, enter your VIN, and click generate
4. **Test in Vehicle**: Try the generated activation key in your navigation system

### 💡 Pro Tip
Generate all 4 possible codes for different system types and write them down. Test each one until you find the correct code to avoid multiple trips to your vehicle!

## 🔧 Technical Details

### Encryption Process
The generator uses a sophisticated encryption process:
1. Extracts map code from the first 8 bytes of CCT.DAT.inf
2. Creates VIN code from characters 8-16 of your VIN
3. Calculates checksums and combines data
4. Performs dual-round Twofish encryption with XOR operations
5. Converts result to activation code using custom character mapping

### File Structure
```
├── index.html              # Main application page
├── assets/
│   ├── css/
│   │   ├── bootstrap.css    # Bootstrap framework styles
│   │   ├── bootstrap-icons.css # Bootstrap icon fonts
│   │   └── style.css        # Custom dark mode styles
│   ├── js/
│   │   ├── bootstrap.bundle.js # Bootstrap JavaScript
│   │   ├── twofish.js       # Twofish encryption library
│   │   ├── generator.js     # Core generation logic
│   │   └── darkmode.js      # Dark mode functionality
│   ├── favicon/
│   │   └── favicon.ico      # Site favicon
│   └── fonts/
│       ├── bootstrap-icons.woff
│       └── bootstrap-icons.woff2
```

## 🚀 Getting Started

Simply open `index.html` in any modern web browser - no installation required!

### Requirements
- Modern web browser with JavaScript enabled
- CCT.DAT.inf file from your map update
- Vehicle VIN number

## 🎨 Dark Mode

The application automatically detects your system's dark mode preference and applies it on first visit. You can manually toggle between light and dark modes using the switch in the header. Your preference is saved locally for future visits.

## 📱 Mobile Support

The interface is fully responsive and works seamlessly on:
- Desktop computers
- Tablets
- Mobile phones

## 🔒 Privacy & Security

- All processing happens locally in your browser
- No data is sent to external servers
- Your VIN and files never leave your device
- No tracking or analytics

## 🙏 Acknowledgments

Special thanks to the following projects and resources that made this possible:

- 🌍 **[Flaticon](https://www.flaticon.com/free-icon/earth_2072130)** - Beautiful earth icon for the favicon
- 🔐 **[wouldgo](https://github.com/wouldgo/twofish/)** - Excellent Twofish JavaScript encryption library
- 🎨 **[Bootstrap Framework](https://getbootstrap.com/docs/5.3/getting-started/download/)** - Modern, responsive CSS framework
- 🎯 **[Bootstrap Icons](https://icons.getbootstrap.com/)** - Comprehensive icon library
- ⚙️ **Unknown Creator of generator.jar** - Original Java implementation that inspired this web-based version
- 📝 **generator.jar Source Code** - Reference implementation that provided the core encryption logic and algorithms

## ⚠️ Disclaimer

This tool is provided for educational and legitimate use only. Users are responsible for ensuring they have the right to activate maps on their vehicles. The developers are not responsible for any misuse or damage that may result from using this tool.

## 📄 License

This project is licensed under the GNU General Public License v3.0 (GPL-3.0).

### Key Points:
- ✅ **Free to use, modify, and distribute**
- 📖 **Open source** - source code must remain available
- 🔄 **Copyleft** - derivative works must also be GPL-3.0 licensed
- 🛡️ **No warranty** - provided "as is" without guarantees

For the full license text, see the [GNU GPL v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).

## 🤖 AI Assistance

This project was made possible with the assistance of **Claude Sonnet 4** - an AI model by Anthropic that helped transform the original Java implementation into this modern web-based tool. Without AI assistance, creating this comprehensive application would not have been possible.

---

**Happy Navigation! 🗺️✨**
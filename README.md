# ☕ Kopi dan Nyemil - Aplikasi Android Pemesanan Kopi & Camilan

**Kopi dan Nyemil** adalah aplikasi mobile Android native yang dirancang untuk memberikan pengalaman pemesanan kopi dan camilan secara cepat, responsif, dan intuitif. Aplikasi ini mengusung desain UI modern berkonsep warna warm-cream & coffee brown, dengan arsitektur UI programmatic menggunakan Kotlin tanpa memerlukan file layout XML yang rumit.

---

## 🌟 Fitur Utama

- **Halaman Selamat Datang & Branding (Welcome Screen):** Tampilan awal dengan desain *aesthetic* dan slogan produk.
- **Autentikasi Pengguna (Sign Up, Login & Register):** 
  - Alur registrasi akun baru (Nama Lengkap, Username, Password).
  - Alur login dengan validasi input dasar.
- **Katalog Menu Interaktif (Home Screen):**
  - Menampilkan daftar produk minuman kopi & makanan ringan beserta deskripsi, harga, dan emoji representatif.
  - Pilihan menu meliputi: *Kopi Susu Gula Aren*, *Donat Cokelat*, *Sourdough Loaf*, *Cappuccino*, dan *Matcha Latte*.
  - Tombol aksi cepat `+` untuk memilih produk langsung ke formulir pemesanan.
- **Formulir Pengiriman & Checkout (Order Screen):**
  - Input Alamat Pengiriman, Nama Penerima, dan Detail/Patokan Lokasi.
  - Ringkasan produk dan harga yang dipilih.
- **Konfirmasi Pesanan (Confirmation Screen):**
  - Tampilan sukses pemesanan dengan ucapan terima kasih dan status pengantaran kurir.
- **Pengaturan Profil (Profile Screen):**
  - Melihat dan memperbarui Nama Pelanggan secara dinamis.
- **Navigasi Bawah Interaktif (Bottom Navigation):**
  - Perpindahan cepat antar halaman (Home ⌂, Order ▣, dan Profile ●).

---

## 🛠️ Teknologi & Environment

- **Bahasa Pemrograman:** Kotlin 2.0.21
- **Platform / SDK:** Android SDK (Min SDK 24, Target SDK 36, Compile SDK 36)
- **UI Architecture:** Programmatic View Creation (Kotlin Dynamic Layouts)
- **Build System:** Gradle 9.6.0 dengan Android Gradle Plugin (AGP) 9.4.0
- **Compatibility:** Java 11 / JDK 17+

---

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat
1. Terinstall **Android Studio** (Android Studio Ladybug 2024.2.1 atau yang lebih baru direkomendasikan).
2. **Android SDK 36** terinstall di Android Studio SDK Manager.
3. **JDK 17** atau **JDK 21** terkonfigurasi pada IDE.
4. Perangkat fisik Android (dengan USB Debugging aktif) atau Android Virtual Device (Emulator) dengan SDK 24+.

### Langkah-Langkah Instalasi & Executing

1. **Clone Repository ini:**
   ```bash
   git clone <repository-url>
   cd KopiDanNyemil
   ```

2. **Buka Project di Android Studio:**
   - Jalankan Android Studio.
   - Pilih **Open** lalu arahkan ke direktori project `KopiDanNyemil`.

3. **Sinkronisasi Gradle:**
   - Android Studio akan secara otomatis mendownload dependensi dan menjalankan Gradle Sync.
   - Jika diperlukan sync manual, klik tombol **Sync Project with Gradle Files** (tombol gajah) di pojok kanan atas.

4. **Jalankan Aplikasi:**
   - Pilih target emulator atau HP Android fisik Anda di bagian toolbar atas.
   - Klik tombol **Run** (segitiga hijau `▶`) atau tekan `Shift + F10`.

---

## 📸 Tangkapan Layar (Screenshots)

| Halaman Welcome | Halaman Login & Registrasi | Halaman Katalog (Home) |
| :---: | :---: | :---: |
| ![Welcome Screen](docs/screenshots/welcome.png) | ![Login Screen](docs/screenshots/login.png) | ![Home Screen](docs/screenshots/home.png) |

| Formulir Order | Konfirmasi Pesanan | Profil Pelanggan |
| :---: | :---: | :---: |
| ![Order Screen](docs/screenshots/order.png) | ![Confirmation Screen](docs/screenshots/confirmation.png) | ![Profile Screen](docs/screenshots/profile.png) |

---

## 📄 Lisensi & Catatan
- **Catatan Tugas:** Aplikasi ini dibuat untuk kebutuhan tugas/studi kasus pemesanan online berbasis Android.
- Data autentikasi dan keranjang belanja dikelola secara in-memory untuk simulasi alur pengguna.

Untuk menjalankan aplikasi parkir ini yang perlu disiapkan :
1. Backend API menggunakan Java Springboot versi 3.5.3
2. Postgres database dengan versi 15 ke bawah, jika menggunakan postgres 16 ada dependency yang tidak support. Karena backend mengaplikasikan flyway yang hanya support di postgres up to 15
3. NPM sudah harus terinstall karena frontend menggunakan Vue.js dan framework quasar.
4. Quasar CLI harus terinstall dengan command berikut 'npm i -g @quasar/cli' dan 'npm init quasar@latest'

Run application :
1. Jalankan java springboot, bisa lewat terminal dengan menjalankan command mvn spring-boot:run -DskipTests
2. Jika ingin menjalankan test saja cukup gunakan command ./mvnw test
3. Jika ingin mengakses api dengan postman/insomnia/SoapUi bisa menggunakan Postman Collection. Terdapat Contoh request untuk api
4. Untuk menjalankan frontend bisa mengakses ke path berikut ..\parking-fe\parking-system-app\dist\electron\Packaged\Application for Parking System-win32-x64 dan double klik pada file Application_for_Parking_System.exe
5. Jika file tersebut gagal, maka perlu menggunakan command dengan mengakses direktori ..\parking-fe\parking-system-app dan jalankan command 'quasar dev'

Saat aplikasi pertama kali dijalankan, maka perlu disiapkan terlebih dahulu user dengan role "Operator Entry", "Operator Exit Gate 1", "Operator Exit Gate 2" sebagai otorisasi user yang bisa mengakses form Entry Gate dan Exit Gate. Pembuatan user bisa langsung mengakses file postman collection dengan url "http://localhost:9095/users/addUser"
Jika user sudah tersedia, maka anda dapat login ke aplikasi
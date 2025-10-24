# Responsi 1 Mobile (H1D023046)

---

## Identitas

Nama : Naufal Aulia Pratama
NIM : H1D0234046
Shift KRS : E
Shift : A

---

## Demo Aplikasi

Aplikasi ini menampilkan beberapa tampilan utama, mulai dari ikon aplikasi di launcher, halaman utama, halaman pelatih, hingga halaman daftar pemain. Berikut merupakan cuplikan demo aplikasi dalam bentuk GIF.

![Demo Aplikasi](demo/demo.gif)


## Fitur Aplikasi

Aplikasi ini terdiri dari tiga bagian utama. Pertama adalah **halaman profil klub**, yang berisi gambar, nama, dan deskripsi singkat tentang klub sepak bola yang dipilih. Kedua adalah **halaman pelatih**, yang menampilkan informasi tentang pelatih kepala atau *head coach* yang datanya diambil langsung dari API. Terakhir adalah **halaman skuad pemain**, yang menampilkan seluruh anggota tim menggunakan komponen **RecyclerView** agar data terlihat lebih terstruktur dan mudah dibaca.

Selain itu, aplikasi ini juga memiliki fitur pewarnaan kartu pemain berdasarkan posisi mereka di lapangan. Pemain dengan posisi **Goalkeeper** memiliki warna latar kuning, **Defender** berwarna biru, **Midfielder** berwarna hijau, dan **Forward** berwarna merah. Pewarnaan ini membantu pengguna membedakan peran setiap pemain secara visual.

---

## Alur Data dari API ke Tampilan

Aplikasi ini diawali dengan proses inisialisasi **Retrofit**, yang didefinisikan di file `network/RetrofitInstance.kt`. File ini berisi pengaturan dasar seperti alamat API utama `https://api.football-data.org/` serta converter untuk mengubah data JSON menjadi objek data Kotlin.  
Selain itu, terdapat **interceptor** pada klien **OkHttp** yang secara otomatis menambahkan **API Token** ke setiap permintaan agar proses otentikasi berjalan dengan benar.

Selanjutnya, interface bernama `ApiService.kt` di dalam folder `network` digunakan untuk mendefinisikan endpoint yang akan diakses, yaitu `GET /v4/teams/{id-klub}`. Endpoint ini memungkinkan aplikasi mengambil data lengkap klub berdasarkan ID tertentu, dalam hal ini **ID 57** untuk klub Arsenal.

Proses pengambilan data dilakukan melalui `TeamRepository.kt`, yang berperan sebagai penghubung antara **ViewModel** dan sumber data. Repository ini memanggil fungsi dari **ApiService** untuk mengambil data dari jaringan, lalu meneruskannya ke **ViewModel**.

Pada file `TeamViewModel.kt`, proses pemanggilan API dijalankan secara asynchronous menggunakan **Kotlin Coroutines** agar tidak mengganggu thread utama. Data yang berhasil diterima kemudian disimpan dalam **LiveData** atau **StateFlow**, sehingga dapat diamati oleh tampilan (*Activity*).  
Model data yang digunakan untuk menyimpan hasil respons berada di dalam `model/TeamModels.kt`.

Setelah data berhasil dimuat, bagian tampilan seperti `CoachActivity.kt` dan `SquadActivity.kt` akan menampilkan informasi sesuai dengan jenis halamannya.  
Pada `CoachActivity`, data seperti nama dan kebangsaan pelatih akan ditampilkan ke dalam komponen **TextView** pada layout `activity_coach.xml`.  
Sementara itu, `SquadActivity` menampilkan daftar pemain dengan bantuan `RecyclerView`, di mana datanya dikelola oleh `SquadAdapter.kt`.

Adapter inilah yang mengatur tampilan setiap item pemain menggunakan layout `list_item_player.xml`. Di dalam fungsi `onBindViewHolder`, adapter akan memeriksa posisi pemain dan memberikan warna latar belakang kartu sesuai dengan ketentuan: kuning untuk goalkeeper, biru untuk defender, hijau untuk midfielder, dan merah untuk forward.

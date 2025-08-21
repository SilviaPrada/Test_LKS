# 📒 Todo App – Kotlin Jetpack Compose

Aplikasi sederhana untuk menampilkan daftar **Todos** menggunakan [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API.  
Dibangun dengan **Kotlin**, **Jetpack Compose**, dan **MVVM (ViewModel + StateFlow)**.

---

## ✨ Fitur
- ✅ Menampilkan daftar todo dari API
- 🔍 Melihat detail todo berdasarkan ID
- 📌 Status todo (`Completed` / `Pending`)
- 🖼️ UI modern dengan **Jetpack Compose Material3**
- 🌐 Networking dengan **Retrofit + Moshi**
- 📊 State management dengan **ViewModel + StateFlow**

---

## 🛠️ Tech Stack
- [Kotlin](https://kotlinlang.org/) – bahasa utama
- [Jetpack Compose](https://developer.android.com/jetpack/compose) – UI toolkit modern
- [Material3](https://m3.material.io/) – desain & komponen UI
- [Retrofit](https://square.github.io/retrofit/) – HTTP client
- [Moshi](https://github.com/square/moshi) – JSON parser
- [OkHttp](https://square.github.io/okhttp/) – HTTP logging interceptor
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) – state management
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) – lifecycle aware data holder

---

## 📂 Struktur Project (ringkas)
```text
kotlin+java/
└── silvia.test.todoapp/
    ├── data/
    │   └── Todo.kt                 # Data model untuk Todo
    │
    ├── network/
    │   ├── ApiService.kt           # Interface Retrofit untuk API
    │   └── RetrofitClient.kt       # Inisialisasi Retrofit
    │
    ├── repository/
    │   └── TodoRepository.kt       # Repository untuk ambil data dari API
    │
    ├── ui/
    │   ├── theme/                  # Kustomisasi tema Jetpack Compose
    │   │   ├── Color.kt
    │   │   ├── Theme.kt
    │   │   └── Type.kt
    │   │
    │   └── view/                   # Layer UI utama
    │       ├── MainActivity.kt
    │       ├── TodoApp.kt
    │       ├── TodoListDetailPane.kt
    │       └── TodoListPane.kt
    │
    └── viewmodel/
        └── TodoViewModel.kt        # ViewModel dengan StateFlow

```
## 🚀 Cara Menjalankan
1. Clone repository ini:
   ```bash
   git clone https://github.com/SilviaPrada/Test_LKS
   cd Test_LKS
Buka project di Android Studio (versi terbaru).

Pastikan compileSdk = 36 atau sesuai rekomendasi terbaru.

Jalankan aplikasi di emulator atau device Android.

📸 Screenshot
<p float="left">
  <img src="https://github.com/user-attachments/assets/c6be971c-c486-4245-843b-49897841c9ff" width="300" />
  <img src="https://github.com/user-attachments/assets/63bffd87-6e03-42f4-8272-976547e13bce" width="300" />
</p>

📜 Lisensi
Aplikasi ini dibuat untuk memenuhi test di PT Lumut Karya Sejahtera.
Dataset API menggunakan JSONPlaceholder (gratis & publik).

# Fungsi untuk menentukan prioritas operator
def precedence(op):
    # Jika operator + atau -, prioritas rendah
    if op in ('+', '-'):
        return 1
    # Jika operator * atau /, prioritas lebih tinggi
    if op in ('*', '/'):
        return 2
    # Selain itu (misalnya tanda kurung), prioritas 0
    return 0

# Fungsi untuk mengubah infix menjadi postfix
def infix_to_postfix(infix):
    stack = []      # Stack untuk menyimpan operator
    postfix = []    # List untuk hasil postfix

    # Memisahkan input berdasarkan spasi (token)
    tokens = infix.split()

    # Loop setiap token
    for token in tokens:
        # Jika token adalah angka, langsung masuk ke postfix
        if token.isdigit():
            postfix.append(token)

        # Jika token adalah tanda kurung buka
        elif token == '(':
            stack.append(token)

        # Jika token adalah tanda kurung tutup
        elif token == ')':
            # Pop semua operator sampai ketemu '('
            while stack and stack[-1] != '(':
                postfix.append(stack.pop())
            stack.pop()  # Menghapus '(' dari stack

        # Jika token adalah operator (+, -, *, /)
        else:
            # Selama stack tidak kosong dan prioritas operator di atas stack >= operator sekarang
            while stack and precedence(stack[-1]) >= precedence(token):
                postfix.append(stack.pop())
            # Masukkan operator ke stack
            stack.append(token)

    # Setelah semua token diproses, keluarkan sisa operator di stack
    while stack:
        postfix.append(stack.pop())

    return postfix  # Mengembalikan hasil postfix

# Fungsi untuk menghitung hasil dari postfix
def evaluate_postfix(postfix):
    stack = []  # Stack untuk perhitungan
    print("\nSTEP BY STEP EVALUASI:")

    # Loop setiap token postfix
    for token in postfix:
        # Jika token adalah angka
        if token.isdigit():
            stack.append(int(token))  # Masukkan ke stack
            print(f"Push {token}")

        # Jika token adalah operator
        else:
            # Ambil dua nilai teratas dari stack
            b = stack.pop()
            a = stack.pop()

            # Lakukan operasi sesuai operator
            if token == '+':
                result = a + b
            elif token == '-':
                result = a - b
            elif token == '*':
                result = a * b
            elif token == '/':
                result = a // b  # Pembagian bilangan bulat

            # Simpan hasil kembali ke stack
            stack.append(result)
            print(f"Pop {a} dan {b}, hitung {a}{token}{b} = {result}")

    # Hasil akhir ada di elemen terakhir stack
    return stack[-1]

# 🔁 LOOP PROGRAM UTAMA
while True:
    # Input ekspresi infix dari user
    infix = input("\nMasukkan ekspresi infix (gunakan spasi, contoh: 12 + 3 * 5): ")

    # Konversi ke postfix
    postfix = infix_to_postfix(infix)

    # Menampilkan hasil postfix
    print("\nPostfix Expression:", " ".join(postfix))

    # Evaluasi postfix
    hasil = evaluate_postfix(postfix)
    print("\nHasil Akhir:", hasil)

    # Pilihan untuk lanjut atau keluar program
    pilihan = input("\nIngin lanjut? (y/n): ").lower()
    if pilihan != 'y':
        print("Program selesai.")
        break

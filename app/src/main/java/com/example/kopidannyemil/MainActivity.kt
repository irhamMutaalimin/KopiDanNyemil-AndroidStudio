package com.example.kopidannyemil

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*
import android.text.InputType

class MainActivity : Activity() {

    private val cream = Color.rgb(250, 247, 242)
    private val brown = Color.rgb(91, 55, 34)
    private val dark = Color.rgb(35, 30, 27)
    private val muted = Color.rgb(130, 119, 109)
    private val accent = Color.rgb(190, 128, 76)
    private val green = Color.rgb(43, 153, 91)

    private lateinit var root: FrameLayout
    private var customerName = "Pelanggan"
    private var selectedMenu = "Kopi Susu Gula Aren"
    private var selectedPrice = "Rp 22.000"

    private data class MenuItem(val name: String, val price: String, val emoji: String, val description: String)
    private val menus = listOf(
        MenuItem("Kopi Susu Gula Aren", "Rp 22.000", "☕", "Espresso, susu, dan gula aren"),
        MenuItem("Donat Cokelat", "Rp 12.000", "🍩", "Donat lembut dengan cokelat"),
        MenuItem("Sourdough Loaf", "Rp 35.000", "🍞", "Roti sourdough panggang"),
        MenuItem("Cappuccino", "Rp 25.000", "🥤", "Espresso dengan foam susu"),
        MenuItem("Matcha Latte", "Rp 27.000", "🍵", "Matcha premium dan susu")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        root = findViewById(R.id.rootContainer)
        showWelcome()
    }

    private fun page(): LinearLayout {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(24), dp(22), dp(24), dp(18))
            setBackgroundColor(cream)
        }
        root.removeAllViews()
        root.addView(layout, FrameLayout.LayoutParams(-1, -1))
        return layout
    }

    private fun scrollPage(): Pair<LinearLayout, LinearLayout> {
        val outer = page()
        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        scroll.addView(content)
        outer.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        return outer to content
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    private fun rounded(color: Int, radius: Int = 18, stroke: Int? = null): GradientDrawable =
        GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
            if (stroke != null) setStroke(dp(1), stroke)
        }

    private fun text(value: String, size: Float, color: Int = dark, bold: Boolean = false): TextView =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(typeface, Typeface.BOLD)
            setLineSpacing(dp(2).toFloat(), 1f)
        }

    private fun addTitle(parent: LinearLayout, title: String, subtitle: String? = null) {
        parent.addView(text(title, 28f, brown, true), LinearLayout.LayoutParams(-1, -2).apply {
            bottomMargin = dp(8)
        })
        if (subtitle != null) parent.addView(text(subtitle, 14f, muted), LinearLayout.LayoutParams(-1, -2).apply {
            bottomMargin = dp(24)
        })
    }

    private fun button(parent: LinearLayout, label: String, onClick: () -> Unit, filled: Boolean = true) {
        val b = TextView(this).apply {
            text = label
            textSize = 15f
            gravity = Gravity.CENTER
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(if (filled) Color.WHITE else brown)
            background = rounded(if (filled) dark else Color.TRANSPARENT, 14, if (filled) null else accent)
            setPadding(dp(16), dp(15), dp(16), dp(15))
            isClickable = true
            isFocusable = true
            setOnClickListener { onClick() }
        }
        parent.addView(b, LinearLayout.LayoutParams(-1, dp(52)).apply { topMargin = dp(10) })
    }

    private fun field(parent: LinearLayout, hint: String, password: Boolean = false): EditText {
        val input = EditText(this).apply {
            this.hint = hint
            textSize = 14f
            setTextColor(dark)
            setHintTextColor(muted)
            isSingleLine = !password
            inputType = if (password) InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        else InputType.TYPE_CLASS_TEXT
            background = rounded(Color.WHITE, 12, Color.rgb(229, 220, 210))
            setPadding(dp(15), dp(10), dp(15), dp(10))
        }
        parent.addView(input, LinearLayout.LayoutParams(-1, dp(54)).apply { bottomMargin = dp(12) })
        return input
    }

    private fun spacer(parent: LinearLayout, height: Int) {
        parent.addView(View(this), LinearLayout.LayoutParams(1, dp(height)))
    }

    private fun showWelcome() {
        val p = page()
        val center = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        p.addView(center, LinearLayout.LayoutParams(-1, 0, 1f))
        val logo = TextView(this).apply {
            text = "☕"
            textSize = 54f
            gravity = Gravity.CENTER
            background = rounded(Color.rgb(239, 224, 207), 100)
        }
        center.addView(logo, LinearLayout.LayoutParams(dp(142), dp(142)).apply { gravity = Gravity.CENTER; bottomMargin = dp(28) })
        center.addView(text("Kopi dan Nyemil", 30f, brown, true).apply { gravity = Gravity.CENTER })
        center.addView(text("Get a taste of your wish", 15f, muted).apply {
            gravity = Gravity.CENTER
            setPadding(0, dp(8), 0, dp(28))
        })
        button(center, "Start now", { showSignUp() })
        p.addView(text("Freshly brewed, freshly baked", 12f, muted).apply { gravity = Gravity.CENTER },
            LinearLayout.LayoutParams(-1, dp(34)))
    }

    private fun showSignUp() {
        val (outer, content) = scrollPage()
        spacer(content, 48)
        content.addView(text("☕", 48f, brown).apply { gravity = Gravity.CENTER })
        spacer(content, 24)
        addTitle(content, "Sign up", "Jadi bagian dari Kopi dan Nyemil!\nDaftar untuk menikmati kopi dan camilan favoritmu.")
        button(content, "Register", { showRegister() })
        button(content, "Login", { showLogin() }, false)
    }

    private fun showLogin() {
        val (outer, content) = scrollPage()
        spacer(content, 48)
        addTitle(content, "Welcome back!", "Login untuk melanjutkan dan menikmati menu favoritmu.")
        val username = field(content, "Username")
        val password = field(content, "Password", true)
        button(content, "Login", {
            if (username.text.isNullOrBlank() || password.text.isNullOrBlank()) {
                toast("Username dan password wajib diisi")
            } else {
                if (customerName == "Pelanggan") customerName = username.text.toString()
                showHome()
            }
        })
        button(content, "Kembali", { showSignUp() }, false)
    }

    private fun showRegister() {
        val (outer, content) = scrollPage()
        spacer(content, 28)
        addTitle(content, "Buat akun", "Isi data berikut untuk menjadi member Kopi dan Nyemil.")
        val name = field(content, "Nama lengkap")
        val username = field(content, "Username")
        val password = field(content, "Password", true)
        button(content, "Register", {
            if (name.text.isNullOrBlank() || username.text.isNullOrBlank() || password.text.isNullOrBlank()) {
                toast("Semua field wajib diisi")
            } else {
                customerName = name.text.toString()
                toast("Registrasi berhasil!")
                showHome()
            }
        })
        button(content, "Kembali", { showSignUp() }, false)
    }

    private fun showHome() {
        val (outer, content) = scrollPage()
        content.addView(text("Kopi dan Nyemil", 14f, accent, true))
        content.addView(text("Halo $customerName,", 28f, brown, true), LinearLayout.LayoutParams(-1, -2).apply {
            topMargin = dp(6); bottomMargin = dp(4)
        })
        content.addView(text("Mau ngemil apa hari ini?", 14f, muted), LinearLayout.LayoutParams(-1, -2).apply {
            bottomMargin = dp(22)
        })
        menus.forEach { item ->
            val card = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                background = rounded(Color.WHITE, 16)
                setPadding(dp(14), dp(13), dp(12), dp(13))
            }
            val emoji = text(item.emoji, 30f).apply { gravity = Gravity.CENTER }
            card.addView(emoji, LinearLayout.LayoutParams(dp(54), dp(58)))
            val info = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
            info.addView(text(item.name, 15f, dark, true))
            info.addView(text(item.description, 11f, muted))
            info.addView(text(item.price, 13f, brown, true).apply { setPadding(0, dp(5), 0, 0) })
            card.addView(info, LinearLayout.LayoutParams(0, -2, 1f).apply { leftMargin = dp(10) })
            val add = TextView(this).apply {
                text = "+"
                textSize = 24f
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
                background = rounded(accent, 12)
            }
            card.addView(add, LinearLayout.LayoutParams(dp(40), dp(40)))
            add.setOnClickListener {
                selectedMenu = item.name
                selectedPrice = item.price
                showOrder()
            }
            content.addView(card, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = dp(12) })
        }
        addBottomNav(outer, "Home")
    }

    private fun showProfile() {
        val (outer, content) = scrollPage()
        spacer(content, 20)
        addTitle(content, "Profile Saya", "Profile Setting")
        val name = field(content, "Nama")
        name.setText(if (customerName == "Pelanggan") "" else customerName)
        button(content, "Save", {
            if (name.text.isNullOrBlank()) toast("Nama tidak boleh kosong")
            else {
                customerName = name.text.toString()
                toast("Profile berhasil disimpan")
                showProfile()
            }
        })
        addBottomNav(outer, "Profile")
    }

    private fun showOrder() {
        val (outer, content) = scrollPage()
        spacer(content, 12)
        addTitle(content, "Alamat Pengiriman", "Lengkapi alamat agar pesanan sampai dengan tepat.")
        content.addView(text("Pesanan: $selectedMenu • $selectedPrice", 14f, brown, true),
            LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = dp(18) })
        val name = field(content, "Nama Lengkap")
        name.setText(if (customerName == "Pelanggan") "" else customerName)
        val address = field(content, "Alamat")
        val detail = field(content, "Patokan atau detail lengkap")
        button(content, "Order dan Kirim", {
            if (name.text.isNullOrBlank() || address.text.isNullOrBlank() || detail.text.isNullOrBlank()) {
                toast("Lengkapi semua data pengiriman")
            } else {
                customerName = name.text.toString()
                showConfirmation()
            }
        })
        button(content, "Kembali ke Home", { showHome() }, false)
        addBottomNav(outer, "Order")
    }

    private fun showConfirmation() {
        val p = page()
        val center = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        p.addView(center, LinearLayout.LayoutParams(-1, 0, 1f))
        val check = TextView(this).apply {
            text = "✓"
            textSize = 54f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            background = rounded(green, 100)
        }
        center.addView(check, LinearLayout.LayoutParams(dp(116), dp(116)).apply {
            gravity = Gravity.CENTER
            bottomMargin = dp(18)
        })
        center.addView(text("OK", 24f, green, true).apply { gravity = Gravity.CENTER })
        center.addView(text(
            "Terima kasih sudah memesan tunggu aja sebentar kurir kami segera mengantarkan pesananmu!",
            15f, muted
        ).apply {
            gravity = Gravity.CENTER
            setPadding(dp(12), dp(16), dp(12), dp(20))
        })
        button(center, "Oke", { showHome() })
    }

    private fun addBottomNav(parent: LinearLayout, selected: String) {
        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            background = rounded(Color.WHITE, 16)
            setPadding(dp(4), dp(8), dp(4), dp(8))
        }
        listOf("Home", "Order", "Profile").forEach { label ->
            val item = TextView(this).apply {
                text = when (label) {
                    "Home" -> "⌂\nHome"
                    "Order" -> "▣\nOrder"
                    else -> "●\nProfile"
                }
                textSize = 12f
                gravity = Gravity.CENTER
                setTypeface(typeface, if (label == selected) Typeface.BOLD else Typeface.NORMAL)
                setTextColor(if (label == selected) accent else muted)
                setPadding(dp(8), dp(3), dp(8), dp(3))
                setOnClickListener {
                    when (label) {
                        "Home" -> showHome()
                        "Order" -> showOrder()
                        "Profile" -> showProfile()
                    }
                }
            }
            nav.addView(item, LinearLayout.LayoutParams(0, dp(52), 1f))
        }
        parent.addView(nav, LinearLayout.LayoutParams(-1, dp(64)).apply { topMargin = dp(10) })
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

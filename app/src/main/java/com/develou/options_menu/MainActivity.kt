package com.develou.options_menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var visibleSearch: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.send_button).setOnClickListener {
            visibleSearch = false
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.add("Ayuda")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search, R.id.add_category, R.id.add_label -> {
                showOption(item.title)
                true
            }
            R.id.settings -> {
                goToSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showOption(title: CharSequence) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
    }

    private fun goToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun createCategory(item:MenuItem){
        Log.d("android:onClick", "createCategory()")
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val findItem = menu?.findItem(R.id.search)
        findItem?.isVisible = visibleSearch
        return true
    }

    override fun onStop() {
        super.onStop()
        visibleSearch = true
        invalidateOptionsMenu()
    }
}
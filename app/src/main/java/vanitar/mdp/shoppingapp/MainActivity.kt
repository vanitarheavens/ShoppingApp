package vanitar.mdp.shoppingapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vanitar.mdp.shoppingapp.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val products = ArrayList<Product>()
        products.add(Product("iPad", "iPad Pro 11-inch", 400.0).apply { position = 0 })
        products.add(Product("MacBook M3 Pro", "12-core CPU\n18-core GPU", 2500.00).apply { position = 1 })
        products.add(Product("Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.00).apply { position = 2 })
        products.add(Product("Logitech Keyboard", "Logitech - PRO X\nTKL LIGHTSPEED Wireless", 199.00).apply { position = 3})
        products.add(Product("MacBook M3 Max", "14-core CPU\n30-core GPU", 3499.00).apply { position = 4})

        val imageList = listOf(
            getDrawable(R.drawable.ipad),
            getDrawable(R.drawable.macbook),
            getDrawable(R.drawable.delloinspiron),
            getDrawable(R.drawable.logiteckeyboard),
            getDrawable(R.drawable.macbook)
        )

        val logList = listOf(
            getDrawable(R.drawable.applelog),
            getDrawable(R.drawable.applelog),
            getDrawable(R.drawable.delllog),
            getDrawable(R.drawable.logiteclog),
            getDrawable(R.drawable.applelog)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(products, imageList, logList)

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.onItemClick = { clickedProduct ->
            val intent = Intent(this, DetailsActivity::class.java)

            val bitmap = (imageList[clickedProduct.position] as BitmapDrawable).bitmap


            val file = File(applicationContext.cacheDir, "image.jpg")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()

            intent.putExtra("product", clickedProduct)

            startActivity(intent)
        }
    }
}

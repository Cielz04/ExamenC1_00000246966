package rodriguez.enrique.examenc1_00000246966

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var contador = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Declaración de variables
        val etCantidad = findViewById<EditText>(R.id.eTCantidad)
        val etPrecio = findViewById<EditText>(R.id.eTPrecio)
        val etProducto = findViewById<EditText>(R.id.etProducto)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarProducto)
        val btnCalcular = findViewById<Button>(R.id.button2)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)

        //Datos en la tabla
        val tvProducto1 = findViewById<TextView>(R.id.tvProducto1)
        val tvP1Precio = findViewById<TextView>(R.id.tvP1Precio)
        val tvProducto2 = findViewById<TextView>(R.id.tvProducto2)
        val tvP2Precio = findViewById<TextView>(R.id.tvP2Precio)
        val tvProducto3 = findViewById<TextView>(R.id.tvProducto3)
        val tvP3Precio = findViewById<TextView>(R.id.tvP3Precio)

        //Datos calculados
        val tvSubtotal = findViewById<TextView>(R.id.tvSubtotal)
        val tvIVA = findViewById<TextView>(R.id.tvIVA)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        //Boton para agregar a la tabla
        btnAgregar.setOnClickListener {
            try {
                val producto = etProducto.text.toString()
                val cantidad = etCantidad.text.toString().toInt()
                val precio = etPrecio.text.toString().toDouble()

                val subtotalProducto = precio*cantidad
                when (contador) {
                    1 -> {
                        tvProducto1.setText(producto)
                        tvP1Precio.setText(subtotalProducto.toString())
                    }
                    2 -> {
                        tvProducto2.setText(producto)
                        tvP2Precio.setText(subtotalProducto.toString())
                    }
                    3 -> {
                        tvProducto3.setText(producto)
                        tvP3Precio.setText(subtotalProducto.toString())
                    }
                    else -> {

                    }
                }
                contador++

            } catch (e: NumberFormatException) {
                val tvErrores = findViewById<TextView>(R.id.tvErrores)
                tvErrores.setText("Ingrese 3 productos")
            }
        }

        btnCalcular.setOnClickListener {

            if (tvP1Precio.text.isEmpty() || tvP2Precio.text.isEmpty() || tvP3Precio.text.isEmpty()) {
                val tvErrores = findViewById<TextView>(R.id.tvErrores)
                tvErrores.text = "Por favor, ingrese todos los productos."
                return@setOnClickListener // No continuar con el cálculo
            }

            val tvP1Precio = findViewById<TextView>(R.id.tvP1Precio)
            val tvP2Precio = findViewById<TextView>(R.id.tvP2Precio)
            val tvP3Precio = findViewById<TextView>(R.id.tvP3Precio)

            try {
                val p1Precio = tvP1Precio.text.toString().toDoubleOrNull() ?: 0.0
                val p2Precio = tvP2Precio.text.toString().toDoubleOrNull() ?: 0.0
                val p3Precio = tvP3Precio.text.toString().toDoubleOrNull() ?: 0.0

                val subtotal = p1Precio + p2Precio + p3Precio
                tvSubtotal.text = subtotal.toString()

                val iva = subtotal *0.16
                tvIVA.text = iva.toString()

                val total = subtotal+iva
                tvTotal.text = total.toString()

            } catch (e: Exception) {
                val tvErrores = findViewById<TextView>(R.id.tvErrores)
                tvErrores.setText("Ingrese 3 productos")
            }
        }

        btnLimpiar.setOnClickListener {
            tvProducto1.text =""
            tvP1Precio.text =""
            tvProducto2.text =""
            tvP2Precio.text =""
            tvProducto3.text =""
            tvP3Precio.text =""

            tvSubtotal.text =""
            tvIVA.text =""
            tvTotal.text =""

            contador = 1
        }
            
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
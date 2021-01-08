package com.gabrielcarneiro.jogodaforca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  var tentativasRestantes = 10
    val palavra ="ABACATE"
    var forca = arrayListOf<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (letra in palavra){
            forca.add("_")
        }


        button.setOnClickListener {
            val palpite = palpiteTXT.text.toString()
            var acertou = false

            for((i, letra )in palavra.withIndex()){
                //acertou
                if (letra.toString() == palpite){
                   acertou = true
                    forca[i] = palpite
                }

            }

            var forcaFormatada= ""
            for(letra in forca){
                forcaFormatada += "$letra "

            }
            forcaFormatada =  forcaFormatada.subSequence(0, forcaFormatada.length - 1).toString()
            forcaTXT.text = forcaFormatada
            if(!acertou){
                tentativasRestantes--
                chancesTXT.text = "$tentativasRestantes chances"
                letrasErradasTXT.text = "${letrasErradasTXT.text} $palpite"
                if(tentativasRestantes == 0){

                    Toast.makeText(this, getString(R.string.perdeu_o_jogo), Toast.LENGTH_LONG).show()
                    //TODO limpar a tela e recomeçar
                }else {

                    Toast.makeText(this, getString(R.string.palpite_incorreto), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
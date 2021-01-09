package com.gabrielcarneiro.jogodaforca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

  var tentativasRestantes = 10
    var palavra =""
    var qtdAcertos = 0
    val palavrasPossiveis  = arrayListOf("MESA", "CADEIRA", "BANANA", "CHOCOLATE", "MACONHA", "GUITARRA", "FOGUEIRA", "GELADEIRA")
    var forca = arrayListOf<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

       setupInicial()

        restartBTN.setOnClickListener {
            setupInicial()
        }
        restartFinalBTN.setOnClickListener {
            setupInicial()
        }

            button.setOnClickListener {
            val palpite = palpiteTXT.text.toString()
            palpiteTXT.setText("")
            var acertou = false

            for((i, letra )in palavra.withIndex()){
                //acertou
                if (letra.toString().toUpperCase() == palpite.toUpperCase()){
                   acertou = true
                    forca[i] = palpite.toUpperCase()
                    qtdAcertos++
                }


            }

           formatarForcaEMostrar()
            if(acertou){
                if(qtdAcertos == palavra.length) {

                    Toast.makeText(this, "Parabéns, você ganhou", Toast.LENGTH_LONG).show()
                aperteRestart()
                }
            }else{

                tentativasRestantes--
                chancesTXT.text = "$tentativasRestantes chances"
                letrasErradasTXT.text = "${letrasErradasTXT.text} $palpite"

                if(tentativasRestantes <= 0){

                    Toast.makeText(this, getString(R.string.perdeu_o_jogo), Toast.LENGTH_LONG).show()
                    aperteRestart()
                }else {

                    Toast.makeText(this, getString(R.string.palpite_incorreto), Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun setupInicial() {

       forca.clear()
        qtdAcertos = 0
        tentativasRestantes = 10
        chancesTXT.text = "$tentativasRestantes Chances"
        aindatemTXT.text =" Você ainda tem"
        restartFinalBTN.visibility = GONE

      val index = ( Math.random() * palavrasPossiveis.size).toInt()
        palavra = palavrasPossiveis[index]
        for (letra in palavra){
            forca.add("_")
            formatarForcaEMostrar()
        }
        tentativasRestantes = 10
        letrasErradasTXT.text = ""


    }
    fun restartGame(){
        restartBTN.setOnClickListener{
            setupInicial()
        }

    }
    fun aperteRestart(){

        aindatemTXT.text = "CLICK EM REINICIAR PARA UMA NOVA PARTIDA"
        chancesTXT.text = ""
        restartFinalBTN.visibility = VISIBLE

    }
    private fun formatarForcaEMostrar() {
        var forcaFormatada= ""
        for(letra in forca){
            forcaFormatada += "$letra "

        }
        forcaFormatada =  forcaFormatada.subSequence(0, forcaFormatada.length - 1).toString()
        forcaTXT.text = forcaFormatada
    }
}
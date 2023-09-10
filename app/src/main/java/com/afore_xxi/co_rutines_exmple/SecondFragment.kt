package com.afore_xxi.co_rutines_exmple

import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import com.afore_xxi.co_rutines_exmple.databinding.FragmentSecondBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fun main() = runBlocking { // this: CoroutineScope
            launch {
                doWorld()
                Log.d("JOHN","LISTO")
            }
           }

        binding.buttonSecond.setOnClickListener {
            //main()
            CoroutineScope(IO).launch{
                delay(2000L)
                makeRequest()
            }
            makeToast("PROCESOS CONTINUANDO")
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    // this is your first suspending function
    // A coroutineScope builder can be used inside any suspending function to perform multiple concurrent operations
    suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            Log.d("JOHN","World 2")
        }
        launch {
            delay(1000L)
            Log.d("JOHN","World 1")
        }
        Log.d("JOHN","Hello")
    }

    suspend fun makeRequest(){
    doSomeHTTPRequest("http://www.johncastle.com.mx/")
    }

    private fun makeToast(success: String) {
        Toast.makeText(activity,"RESPUESTA > $success",LENGTH_SHORT).show() // main coroutine continues while a previous one is delayed
    }


    private fun doSomeHTTPRequest(url: String) {
         val callNotificacionExp = ModelKotlin().getFinalURL(url)!!.apiPrueba()
        callNotificacionExp?.enqueue(object : Callback<ApiResult?> {
            override fun onResponse(
                call: Call<ApiResult?>,
                response: Response<ApiResult?>
            ) {

                if (response.isSuccess) {
                    val StatusCode =Integer.parseInt(response.body()!!.StatusCode)
                    if (StatusCode==200) {
                        Log.e("kinder", "OKEY")
                        binding.textviewSecond.text=response.body()!!.StatusMessage
                        makeToast("OKEY")
                    } else {

                        Log.e("kinder", "Error")
                        makeToast("Error")
                    }
                }
                else{
                    Log.e("kinder","No fue posible acceder.")
                    makeToast("No fue posible acceder.")
                }
            }

            override fun onFailure(call: Call<ApiResult?>, t: Throwable) {
                Log.e("kinder","Ha ocurrido un error al consultar la información."+t.message)
                makeToast("Ha ocurrido un error al consultar la información.")

            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
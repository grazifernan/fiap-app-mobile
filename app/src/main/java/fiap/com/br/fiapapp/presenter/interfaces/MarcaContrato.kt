package fiap.com.br.fiapapp.presenter.interfaces

import android.app.DownloadManager
import android.content.Intent
import com.google.firebase.firestore.Query
import fiap.com.br.fiapapp.model.Marca

interface MarcaContrato {


    interface ListaMarcaView{
        fun demonstraMarcas(marcas: ArrayList<String>)
        fun demonstrarMarcaSelecionada (codMarca: Int, descricao: String)
        fun demonstrarMsgErro(msg: String)

    }

    interface ListaMarcaPresenter{
         fun obtemMarca()
         fun obtemMarcaPorNome(nome: String) : Int?
         fun destruirView()
         fun obterMarcaSelecionada(descricao: String)
    }
}
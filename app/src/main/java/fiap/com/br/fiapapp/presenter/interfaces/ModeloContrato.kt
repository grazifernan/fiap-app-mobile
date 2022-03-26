package fiap.com.br.fiapapp.presenter.interfaces

import com.google.firebase.firestore.Query
import fiap.com.br.fiapapp.model.Empresa
import fiap.com.br.fiapapp.model.Marca
import fiap.com.br.fiapapp.model.Modelo

interface ModeloContrato {


    interface ModeloView{
        fun demonstraModelos(modelos: ArrayList<String>)
        fun demonstrarModelosMarcaSel(modelos: ArrayList<String>, descrModeloSel: String)
        fun demonstrarMsgErro(msg: String)
        fun carregarModelos(modelos: ArrayList<Modelo>)

    }

    interface ModeloPresenter{
        fun obtemModelo(codMarca: Int?)
        fun obtemModeloMarcaSel(codMarca: Int?, descrModeloSel: String)
        fun obtemTodosModelos()
        fun obtemModeloPorNome(Nome: String) : Int?
        fun destruirView()
    }
}
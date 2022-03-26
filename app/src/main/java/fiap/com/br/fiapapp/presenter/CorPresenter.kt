package fiap.com.br.fiapapp.presenter

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import fiap.com.br.fiapapp.model.*
import fiap.com.br.fiapapp.presenter.interfaces.CorContrato
import kotlin.collections.ArrayList

open class CorPresenter: CorContrato.ListaCorPresenter {

    private var view: CorContrato.ListaCorView?
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var db: CollectionReference
    private var spinnerArrayList = ArrayList<String>()

    constructor(view: CorContrato.ListaCorView){
        this.view = view
       // this.firebaseFirestore = firebaseFirestore
    }

    override fun obtemCor() {
        var spinnerArrayList = ArrayList<String>()
        spinnerArrayList.add("Selecione")

        FirebaseFirestore.getInstance().collection("cor_veiculo").get()
            .addOnCompleteListener(OnCompleteListener {

            for (dataObject in it.getResult()!!.documents){
                var cor = dataObject.toObject(Cor::class.java)!!
                spinnerArrayList.add(cor.descricao)
            }
            view?.demonstraCores(spinnerArrayList)
        })
            .addOnFailureListener { _ ->
                view?.demonstrarMsgErro("Erro ao carregas as cores disponíveis")
            }

    }

    override fun obterCorSelecionada(descricao: String) {

        var cor = Cor()
        db = FirebaseFirestore.getInstance().collection("cor_veiculo")
        db.whereEqualTo("descricao", descricao).get()

            .addOnCompleteListener(OnCompleteListener {

                for (dataObject in it.getResult()!!.documents){
                    cor = dataObject.toObject(Cor::class.java)!!
                    view?.demonstrarCorSelecionada(cor.cod_cor, cor.descricao)

                }
            })
            .addOnFailureListener {
                    _ ->
            }

    }

    override fun obterCodDescrCor(cor: Cor): Query {
        db = FirebaseFirestore.getInstance().collection("cor_veiculo");
        var qry: Query = db


        if ((cor.cod_cor <=0) &&
            (cor.descricao.isEmpty()) ) {
            qry = db.whereGreaterThan("cod_cor", -1);
            qry = qry.orderBy("descricao")

        }
        if (cor.cod_cor > 0) {
            qry = db.whereEqualTo("cod_cor", cor.cod_cor)
        }

        if (cor.descricao.isNotEmpty()) {
            qry = qry.whereEqualTo("descricao",  cor.descricao);
        }

        return qry
    }


    override fun destruirView() {
        this.view = null
    }



}
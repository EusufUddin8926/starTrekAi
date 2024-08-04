package ltd.v2.starTrekAi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GrammarResponse (

    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : GrammarCheckedData?   = GrammarCheckedData()

): Serializable



data class GrammarCheckedData (

    @SerializedName("result" ) var result : String? = null

)
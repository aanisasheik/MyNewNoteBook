package model
data class Notes(val topic : String,var content : String){
    constructor(): this("","")
}
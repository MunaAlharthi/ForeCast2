package com.test.forecast

class DataModel {
    var date: String = ""
    var titile: String = ""
    var temp: String = ""
    var pic: String = ""

    constructor() {}

    constructor(date: String, titile: String, temp: String, pic: String) {
        this.date = date
        this.titile = titile
        this.temp = temp
        this.pic = pic
    }
}
package com.example.myapp_zkouska

class dbStanice (val id: Int, val stkNazev: String = "", val ulice: String = "", val mesto: String = "", val telefon: String = "", val email: String = "", val web: String = ""){

    companion object {
        val dbList = listOf(
            dbStanice(1,"TKV Liberec spol. s r.o.", "Tanvaldská 1106", "Liberec", "482 737 888", "info@tkv-liberec.cz", "https://www.tkv-liberec.cz"),
            dbStanice(2,"STEKO LB, spol. s r.o.", "Mrštíkova 2", "Liberec", "485 104 111", "stk@stkliberec.cz", "https://www.stkliberec.cz"),
            dbStanice(2,"KLIMEX s.r.o.", "Londýnská 590/85", "Liberec", "485 108 325", "liberec@stkcl.cz", "https://www.stkcl.cz"),
            dbStanice(2,"AGRO SLATINY a.s.", "Hradecká 1122", "Jičín", "493 555 055", "stk@jicinet.cz", "https://www.stk-jicin.cz"),
            dbStanice(2,"STK Turnov, s.r.o.", "Nudvojovická 1681", "Turnov", "481 325 637", "stkturnov@tiscali.cz", "https://www.stkturnov.cz"),
        )
    }
}
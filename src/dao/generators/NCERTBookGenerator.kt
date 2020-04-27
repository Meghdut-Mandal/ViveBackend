package dao.generators

import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import model.Book


object NCERTBookGenerator {
    data class Part(var value: String, var text: String)
    data class IntPart(var value: Int)


   private fun readBook(tclass: IntPart, tSubject: Part, tBook: Part): Book {
        val clazz = tclass.value
        val querryPram = tBook.value.substringAfter("?")
        val name = tBook.text
        val subject = tSubject.text
        return Book("", clazz, name, subject, querryPram)
    }


    fun books() =
        flow {
            (1..12).map {
                IntPart(it)
            }.forEach { tClass ->
                changeSubject(tClass)
                tsubjectOptions.values.filter { it.text.isNotBlank() && !it.text.contains("Select")}.forEach { tSubject ->
                    changeBook(tClass, tSubject)
                    tbookOptions.values.filter { it.text.isNotBlank() && !it.text.contains("Select") }.forEach {
//                        println("ncert>>main ${tClass.value} $tSubject   $it ")
                        emit(readBook(tClass, tSubject, it))
                    }
                }
            }
        }.buffer()


   private val tbookOptions = hashMapOf<Int, Part>().apply {
        (0..30).map { this[it] = Part("", "") }
    }


    private val tsubjectOptions = hashMapOf<Int, Part>().apply {
        (0..30).map { this[it] = Part("", "") }
    }

   private fun changeSubject(tclass: IntPart) {

        //this function check the classthat you have selected
        tsubjectOptions.values.forEach {
            it.text = ""
            it.value = ""
        }
        if (tclass.value == -1) {
            tsubjectOptions[0]?.text = ".....Select Subject.....";
            tsubjectOptions[1]?.text = "";
            tsubjectOptions[2]?.text = "";

        } else if (tclass.value == 1)// this condition checks which class u have select and you can add subject according to class 1
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "English";
            tsubjectOptions[2]?.text = "Mathematics";
            tsubjectOptions[3]?.text = "Hindi";
            tsubjectOptions[4]?.text = "Urdu";
            //tsubjectOptions[5]?.text="";

        } else if (tclass.value == 2)// this condition checks which class u have select and you can add subject according to class 2
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Hindi";
            tsubjectOptions[3]?.text = "English"
            tsubjectOptions[4]?.text = "Urdu"

        } else if (tclass.value == 3)// this condition checks which class u have select and you can add subject according to class 3
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Hindi";
            tsubjectOptions[3]?.text = "English"
            tsubjectOptions[4]?.text = "Environmental Studies"
            tsubjectOptions[5]?.text = "Urdu"


        } else if (tclass.value == 4)// this condition checks which class u have select and you can add subject according to class 4
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Hindi";
            tsubjectOptions[3]?.text = "English"
            tsubjectOptions[4]?.text = "Environmental Studies"
            tsubjectOptions[5]?.text = "Urdu"


        } else if (tclass.value == 5)// this condition checks which class u have select and you can add subject according to class 5
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Hindi";
            tsubjectOptions[3]?.text = "English";
            tsubjectOptions[4]?.text = "Environmental Studies";
            tsubjectOptions[5]?.text = "Urdu";
            //tsubjectOptions[6]?.text="";

        } else if (tclass.value == 6)// this condition checks which class u have select and you can add subject according to class  6
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Hindi";
            tsubjectOptions[2]?.text = "English";
            tsubjectOptions[3]?.text = "Mathematics";
            tsubjectOptions[4]?.text = "Social Studies";
            tsubjectOptions[5]?.text = "Sanskrit";
            tsubjectOptions[6]?.text = "Science";
            tsubjectOptions[7]?.text = "Urdu";
            //tsubjectOptions[8]?.text="Environmental Education";
            //tsubjectOptions[9]?.text="";

        } else if (tclass.value == 7)// this condition checks which class u have select and you can add subject according to class 7
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Science";
            tsubjectOptions[3]?.text = "English"
            tsubjectOptions[4]?.text = "Sanskrit"
            tsubjectOptions[5]?.text = "Social Science"
            tsubjectOptions[6]?.text = "Hindi"
            tsubjectOptions[7]?.text = "Urdu"
            //tsubjectOptions[8]?.text="Environmental Education"
        } else if (tclass.value == 8)// this condition checks which class u have select and you can add subject according to class 8
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "English";
            tsubjectOptions[2]?.text = "Mathematics";
            tsubjectOptions[3]?.text = "Hindi";
            tsubjectOptions[4]?.text = "Science";
            tsubjectOptions[5]?.text = "Social Science";
            tsubjectOptions[6]?.text = "Sanskrit";
            tsubjectOptions[7]?.text = "Urdu";
            //tsubjectOptions[7]?.text="Environmental Education";
            //tsubjectOptions[8]?.text="";
            //tsubjectOptions[9]?.text="";

        } else if (tclass.value == 9)// this condition checks which class u have select and you can add subject according to class 9
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "English";
            tsubjectOptions[2]?.text = "Hindi";
            tsubjectOptions[3]?.text = "Sanskrit";
            tsubjectOptions[4]?.text = "Mathematics";
            tsubjectOptions[5]?.text = "Science";
            tsubjectOptions[6]?.text = "Social Science";
            //tsubjectOptions[7]?.text="Art Education";
            tsubjectOptions[7]?.text = "Urdu";
            tsubjectOptions[8]?.text = "Health and Physical Education";
            tsubjectOptions[9]?.text = "ICT";
            //tsubjectOptions[9]?.text="Vocational";
            //tsubjectOptions[8]?.text="Environmental Education";
            //tsubjectOptions[9]?.text="";

        } else if (tclass.value == 10)// this condition checks which class u have select and you can add subject according to class 10
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Science";
            tsubjectOptions[3]?.text = "Hindi";
            tsubjectOptions[4]?.text = "Social Science";
            tsubjectOptions[5]?.text = "English";
            tsubjectOptions[6]?.text = "Sanskrit";
            tsubjectOptions[7]?.text = "Urdu";
            //tsubjectOptions[8]?.text="Environmental Education";

        } else if (tclass.value == 11)// this condition checks which class u have select and you can add subject according to class 11
        {
            /*tsubjectOptions[0]?.text="..Select Subject..";
            tsubjectOptions[1]?.text="Sanskrit";
            tsubjectOptions[2]?.text="Accountancy";
            tsubjectOptions[3]?.text="Chemistry";
            tsubjectOptions[4]?.text="Mathematics";
            tsubjectOptions[5]?.text="Statistics";
            tsubjectOptions[6]?.text="Biology";
            tsubjectOptions[7]?.text="Psychology";
            tsubjectOptions[8]?.text="Geography";
            tsubjectOptions[9]?.text="Physics";
            tsubjectOptions[10]?.text="Hindi";
            tsubjectOptions[11]?.text="Sociology";
            tsubjectOptions[12]?.text="English";
            tsubjectOptions[13]?.text="Political Science";
            tsubjectOptions[14]?.text="History";
            tsubjectOptions[15]?.text="Economics";
            tsubjectOptions[16]?.text="Business Studies";
            tsubjectOptions[17]?.text="Urdu";
               tsubjectOptions[18]?.text="Heritage Crafts";
            tsubjectOptions[19]?.text="Graphics design";
            tsubjectOptions[20]?.text="Computers and Communication Technology";
            tsubjectOptions[21]?.text="Home Science";
            tsubjectOptions[22]?.text="Creative Writing and Translation";
            tsubjectOptions[23]?.text="Fine Art";
            //tsubjectOptions[24]?.text="Computer Science";
            tsubjectOptions[24]?.text="Informatics Practices";
            tsubjectOptions[25]?.text="Computer Science";*/


            tsubjectOptions[0]?.text = "Sanskrit";
            tsubjectOptions[1]?.text = "Accountancy";
            tsubjectOptions[2]?.text = "Chemistry";
            tsubjectOptions[3]?.text = "Mathematics";
            tsubjectOptions[4]?.text = "Statistics";
            tsubjectOptions[5]?.text = "Biology";
            tsubjectOptions[6]?.text = "Psychology";
            tsubjectOptions[7]?.text = "Geography";
            tsubjectOptions[8]?.text = "Physics";
            tsubjectOptions[9]?.text = "Hindi";
            tsubjectOptions[10]?.text = "Sociology";
            tsubjectOptions[11]?.text = "English";
            tsubjectOptions[12]?.text = "Political Science";
            tsubjectOptions[13]?.text = "History";
            tsubjectOptions[14]?.text = "Economics";
            tsubjectOptions[15]?.text = "Business Studies";
            tsubjectOptions[16]?.text = "Urdu";
            tsubjectOptions[17]?.text = "Heritage Crafts";
            tsubjectOptions[18]?.text = "Graphics design";
            tsubjectOptions[19]?.text = "Computers and Communication Technology";
            tsubjectOptions[20]?.text = "Home Science";
            tsubjectOptions[21]?.text = "Creative Writing and Translation";
            tsubjectOptions[22]?.text = "Fine Art";
            //tsubjectOptions[24]?.text="Computer Science";
            tsubjectOptions[23]?.text = "Informatics Practices";
            tsubjectOptions[24]?.text = "Computer Science";


            //tsubjectOptions[24]?.text="Vocational";
        } else if (tclass.value == 12)// this condition checks which class u have select and you can add subject according to class 12
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Mathematics";
            tsubjectOptions[2]?.text = "Physics";
            tsubjectOptions[3]?.text = "Accountancy"
            tsubjectOptions[4]?.text = "Sanskrit"
            tsubjectOptions[5]?.text = "Hindi"
            tsubjectOptions[6]?.text = "English"
            tsubjectOptions[7]?.text = "Biology"
            tsubjectOptions[8]?.text = "History"
            tsubjectOptions[9]?.text = "Geography"
            tsubjectOptions[10]?.text = "Psychology"
            tsubjectOptions[11]?.text = "Sociology"
            tsubjectOptions[12]?.text = "Chemistry";
            tsubjectOptions[13]?.text = "Political Science";
            tsubjectOptions[14]?.text = "Economics";
            tsubjectOptions[15]?.text = "Business Studies";
            tsubjectOptions[16]?.text = "Heritage Crafts";
            tsubjectOptions[17]?.text = "New Age Graphics Design";
            tsubjectOptions[18]?.text = "Home Science";
            tsubjectOptions[19]?.text = "Urdu";

        } else if (tclass.value == 13)// this condition checks which class u have select and you can add subject according to class
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            tsubjectOptions[1]?.text = "Hindi";
            tsubjectOptions[2]?.text = "Sanskrit";
            tsubjectOptions[3]?.text = "Heritage Crafts";

        } else if (tclass.value == 14)// this condition checks which class u have select and you can add subject according to class
        {
            tsubjectOptions[0]?.text = "..Select Subject..";
            //tsubjectOptions[1]?.text="Vocational";

        }
    }


   private fun changeBook(tclass: IntPart, tSubject: Part) {

        tbookOptions.values.forEach {
            it.text = ""
            it.value = ""
        }

        if ((tclass.value == 1) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Marigold";
            tbookOptions[1]?.value = "textbook.htm?aeen1=0-10"
            tbookOptions[2]?.text = "Raindrops";
            tbookOptions[2]?.value = "textbook.htm?aerd1=0-19"


        } else if ((tclass.value == 1) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Math-Magic";
            tbookOptions[1]?.value = "textbook.htm?aemh1=0-13"
            tbookOptions[2]?.text = "Ganit Ka Jaadu";
            tbookOptions[2]?.value = "textbook.htm?ahmh1=0-13"
            tbookOptions[3]?.text = "Riyazi Ka Jadoo-I(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?auri1=0-13"


        } else if ((tclass.value == 1) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Rimjhim";
            tbookOptions[1]?.value = "textbook.htm?ahhn1=0-23"

        } else if ((tclass.value == 1) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ibtedai Urdu-I";
            tbookOptions[1]?.value = "textbook.htm?aulb1=0-27"

        } else if ((tclass.value == 2) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Math-Magic";
            tbookOptions[1]?.value = "textbook.htm?bemh1=0-15"
            tbookOptions[2]?.text = "ganit ka Jadu";
            tbookOptions[2]?.value = "textbook.htm?bhmh1=0-15"
            tbookOptions[3]?.text = "Riyazi ka Jadu-II(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?buri1=0-15"


        } else if ((tclass.value == 2) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Rimjhim";
            tbookOptions[1]?.value = "textbook.htm?bhhn1=0-15"

        } else if ((tclass.value == 2) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Marigold";
            tbookOptions[1]?.value = "textbook.htm?been1=0-10"
            tbookOptions[2]?.text = "Raindrops";
            tbookOptions[2]?.value = "textbook.htm?berd1=0-15"

        } else if ((tclass.value == 2) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ibtedai Urdu-II";
            tbookOptions[1]?.value = "textbook.htm?buib1=0-20"


        } else if ((tclass.value == 3) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Rimjhim";
            tbookOptions[1]?.value = "textbook.htm?chhn1=0-15"


        } else if ((tclass.value == 3) && (tSubject.text == "Environmental Studies")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Looking Around";
            tbookOptions[1]?.value = "textbook.htm?ceap1=0-24"
            tbookOptions[2]?.text = "Aas-Pass";
            tbookOptions[2]?.value = "textbook.htm?chap1=0-24"
            tbookOptions[3]?.text = "Aas-Pass(urdu)";
            tbookOptions[3]?.value = "textbook.htm?cuap1=0-24"


        } else if ((tclass.value == 3) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Marigold";
            tbookOptions[1]?.value = "textbook.htm?ceen1=0-10"


        } else if ((tclass.value == 3) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics";
            tbookOptions[1]?.value = "textbook.htm?cemh1=0-14"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?chmh1=0-14"
            tbookOptions[3]?.text = "Riyazi Ka Jadoo-III(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?curi1=0-14"

        } else if ((tclass.value == 3) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ibtedai Urdu";
            tbookOptions[1]?.value = "textbook.htm?culb1=0-20"


        } else if ((tclass.value == 4) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Math-Magic";
            tbookOptions[1]?.value = "textbook.htm?demh1=0-14"
            tbookOptions[2]?.text = "Ganit Ka Jadu";
            tbookOptions[2]?.value = "textbook.htm?dhmh1=0-14"
            tbookOptions[3]?.text = "Riyazi Ka Jadu(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?duri1=0-14"

        } else if ((tclass.value == 4) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Rimjhim";
            tbookOptions[1]?.value = "textbook.htm?dhhn1=0-14"


        } else if ((tclass.value == 4) && (tSubject.text == "Environmental Studies")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Looking Around(EVS)";
            tbookOptions[1]?.value = "textbook.htm?deap1=0-27"
            tbookOptions[2]?.text = "Aas Paas";
            tbookOptions[2]?.value = "textbook.htm?dhap1=0-27"
            tbookOptions[3]?.text = "Aas-Paas(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?duap1=0-27"

        } else if ((tclass.value == 4) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Marigold";
            tbookOptions[1]?.value = "textbook.htm?deen1=0-9"

        } else if ((tclass.value == 4) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ibtedai Urdu-IV";
            tbookOptions[1]?.value = "textbook.htm?dulb1=0-22"
        } else if ((tclass.value == 5) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Math-Magic";
            tbookOptions[1]?.value = "textbook.htm?eemh1=0-14"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?ehmh1=0-14"
            tbookOptions[3]?.text = "Riyazi Ka Jadoo(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?euma1=0-14"

        } else if ((tclass.value == 5) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Rimjhim";
            tbookOptions[1]?.value = "textbook.htm?ehhn1=0-18"

        } else if ((tclass.value == 5) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Marigold";
            tbookOptions[1]?.value = "textbook.htm?eeen1=0-10"

        } else if ((tclass.value == 5) && (tSubject.text == "Environmental Studies")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Aas-Pass";
            tbookOptions[1]?.value = "textbook.htm?ehap1=0-22"
            tbookOptions[2]?.text = "Looking Around";
            tbookOptions[2]?.value = "textbook.htm?eeap1=0-22"
            tbookOptions[3]?.text = "Ass Pass(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?euev1=0-22"

        } else if ((tclass.value == 5) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ibtedai Urdu Class-V";
            tbookOptions[1]?.value = "textbook.htm?eulb1=0-22"
//            <!--tbookOptions[2]?.text="math-magic-V";
            //tbookOptions[2]?.value="textbook.htm?euma1=0-14"-->
//            <!--tbookOptions[3]?.text="EVS-V (Urdu)";
            //tbookOptions[3]?.value="textbook.htm?euev1=0-22"-->

        } else if ((tclass.value == 6) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Vasant";
            tbookOptions[1]?.value = "textbook.htm?fhvs1=0-17"
            tbookOptions[2]?.text = "Durva";
            tbookOptions[2]?.value = "textbook.htm?fhdv1=0-28"
            tbookOptions[3]?.text = "Bal Ram Katha";
            tbookOptions[3]?.value = "textbook.htm?fhbr1=0-12"

        } else if ((tclass.value == 6) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Honeysuckle";
            tbookOptions[1]?.value = "textbook.htm?fehl1=0-10"
            tbookOptions[2]?.text = "A Pact With The Sun";
            tbookOptions[2]?.value = "textbook.htm?fepw1=0-10"

        } else if ((tclass.value == 6) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics";
            tbookOptions[1]?.value = "textbook.htm?femh1=0-14"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?fhmh1=0-14"
            tbookOptions[3]?.text = "Hisab(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?furi1=0-14"
            //tbookOptions[4]?.text="Exemplar Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?feep1=0-9"
            //tbookOptions[5]?.text="Exemplar Problem(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?fhep1=0-9"


        } else if ((tclass.value == 6) && (tSubject.text == "Social Studies")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "History - Our Past";
            tbookOptions[1]?.value = "textbook.htm?fess1=0-11"
            tbookOptions[2]?.text = "The Earth Our Habitat";
            tbookOptions[2]?.value = "textbook.htm?fess2=0-8"
            tbookOptions[3]?.text = "Hamare Ateet";
            tbookOptions[3]?.value = "textbook.htm?fhss1=0-11"
            tbookOptions[4]?.text = "Social And Political Life";
            tbookOptions[4]?.value = "textbook.htm?fess3=0-9"
            tbookOptions[5]?.text = "Samajik Evem Rajnitik Jeevan";
            tbookOptions[5]?.value = "textbook.htm?fhss3=0-9"
            tbookOptions[6]?.text = "Prithavi Hamara Avas (Bhugol)";
            tbookOptions[6]?.value = "textbook.htm?fhss2=0-8"
            tbookOptions[7]?.text = "Hamare Maazi(Urdu)";
            tbookOptions[7]?.value = "textbook.htm?fuhm1=0-12"
            tbookOptions[8]?.text = "Zameen Hamara Maskan(Urdu)";
            tbookOptions[8]?.value = "textbook.htm?fuzm1=0-8"
            tbookOptions[9]?.text = "Samazi Aur Siyasi Zindagi(Urdu)";
            tbookOptions[9]?.value = "textbook.htm?fuss1=0-9"
        } else if ((tclass.value == 6) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ruchira";
            tbookOptions[1]?.value = "textbook.htm?fhsk1=0-15"

        } else if ((tclass.value == 6) && (tSubject.text == "Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Vigyan";
            tbookOptions[1]?.value = "textbook.htm?fhsc1=0-16"
            tbookOptions[2]?.text = "Science";
            tbookOptions[2]?.value = "textbook.htm?fesc1=0-16"
            tbookOptions[3]?.text = "science-VI(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?fuse1=0-16"
            //tbookOptions[4]?.text="Exemplar Problem";
            //tbookOptions[4]?.value="textbook.htm?feep2=0-16"


        } else if ((tclass.value == 6) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Apni Zuban-VI";
            tbookOptions[1]?.value = "textbook.htm?fuaz1=0-20"
            tbookOptions[2]?.text = "Urdu Guldasta";
            tbookOptions[2]?.value = "textbook.htm?fuug1=0-11"
            tbookOptions[3]?.text = "Jaan Pahechan ";
            tbookOptions[3]?.value = "textbook.htm?fujp1=0-32"
            //tbookOptions[2]?.text="Riyazi-VI";
            //tbookOptions[2]?.value="textbook.htm?furi1=0-14"
            //tbookOptions[3]?.text="science-VI";
            //tbookOptions[3]?.value="textbook.htm?fuse1=0-16"
            //tbookOptions[4]?.text="Hamare Maz";
            //tbookOptions[4]?.value="textbook.htm?fuhm1=0-12"
            //tbookOptions[5]?.text="Zameen Hamara Maskan";
            //tbookOptions[5]?.value="textbook.htm?fuzm1=0-8"
            //tbookOptions[6]?.text="Samazi Aur Siyasi Zindagi";
            //tbookOptions[6]?.value="textbook.htm?fuss1=0-8"-->


        } else if ((tclass.value == 7) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ganit";
            tbookOptions[1]?.value = "textbook.htm?ghmh1=0-15"
            tbookOptions[2]?.text = "Mathmatics";
            tbookOptions[2]?.value = "textbook.htm?gemh1=0-15"
            tbookOptions[3]?.text = "Hisab(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?guma1=0-15"
            //tbookOptions[4]?.text="Exemplar Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?gemp1=0-13"


        } else if ((tclass.value == 7) && (tSubject.text == "Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Science";
            tbookOptions[1]?.value = "textbook.htm?gesc1=0-19"
            tbookOptions[2]?.text = "Vigyan";
            tbookOptions[2]?.value = "textbook.htm?ghsc1=0-18"
            tbookOptions[3]?.text = "Science(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?guse1=0-18"
            //tbookOptions[4]?.text="Exemplar Problems";
            //tbookOptions[4]?.value="textbook.htm?geep1=0-18"


        } else if ((tclass.value == 7) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Honeycomb";
            tbookOptions[1]?.value = "textbook.htm?gehc1=0-10"
            tbookOptions[2]?.text = "An alien Hand Supplementry Reader";
            tbookOptions[2]?.value = "textbook.htm?geah1=0-10"
            //tbookOptions[3]?.text="";
            //tbookOptions[3]?.value=""

        } else if ((tclass.value == 7) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ruchira";
            tbookOptions[1]?.value = "textbook.htm?ghsk1=0-15"
            //tbookOptions[2]?.text="";
            //tbookOptions[2]?.value=""

        } else if ((tclass.value == 7) && (tSubject.text == "Social Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Social and Political Life";
            tbookOptions[1]?.value = "textbook.htm?gess3=0-9"
            tbookOptions[2]?.text = "Samajik aur Rajniti Jeevan";
            tbookOptions[2]?.value = "textbook.htm?ghss3=0-9"
            tbookOptions[3]?.text = "Samajik Aur Siyasi Zindagi";
            tbookOptions[3]?.value = "textbook.htm?guss3=0-9"
            tbookOptions[4]?.text = "Our Pasts-II";
            tbookOptions[4]?.value = "textbook.htm?gess1=0-10"
            tbookOptions[5]?.text = "Hamare Ateet-II";
            tbookOptions[5]?.value = "textbook.htm?ghss1=0-10"
            tbookOptions[6]?.text = "Our Environment";
            tbookOptions[6]?.value = "textbook.htm?gess2=0-9"
            tbookOptions[7]?.text = "Hamara Paryavaran";
            tbookOptions[7]?.value = "textbook.htm?ghss2=0-9"
            tbookOptions[8]?.text = "Hamare Maazi(Urdu)";
            tbookOptions[8]?.value = "textbook.htm?guhm1=0-10"
            tbookOptions[9]?.text = "Hamare Mahol(Urdu))";
            tbookOptions[9]?.value = "textbook.htm?guha1=0-10"

        } else if ((tclass.value == 7) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Vasant";
            tbookOptions[1]?.value = "textbook.htm?ghvs1=0-20"
            tbookOptions[2]?.text = "Durva";
            tbookOptions[2]?.value = "textbook.htm?ghdv1=0-18"
            tbookOptions[3]?.text = "Mahabharat";
            tbookOptions[3]?.value = "textbook.htm?ghmb1=0-1"

        } else if ((tclass.value == 7) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Apni Zaban";
            tbookOptions[1]?.value = "textbook.htm?guaz1=0-21"
            tbookOptions[2]?.text = "Urdu Guldasta-Suppl";
            tbookOptions[2]?.value = "textbook.htm?gugu1=0-15"
            tbookOptions[3]?.text = "Door - Pass";
            tbookOptions[3]?.value = "textbook.htm?gudp1=0-26"


        } else if ((tclass.value == 8) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Honeydew";
            tbookOptions[1]?.value = "textbook.htm?hehd1=0-10"
            tbookOptions[2]?.text = "It So Happend";
            tbookOptions[2]?.value = "textbook.htm?heih1=0-11"
            //tbookOptions[3]?.text="";
            //tbookOptions[3]?.value=""

        } else if ((tclass.value == 8) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics";
            tbookOptions[1]?.value = "textbook.htm?hemh1=0-16"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?hhmh1=0-16"
            tbookOptions[3]?.text = "Riyazi(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?huhi1=0-16"
            //tbookOptions[4]?.text="Exemplar Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?heep2=0-13"
            //tbookOptions[5]?.text="Exemplar Problem(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?hhep2=0-13"
            //tbookOptions[3]?.text="Hisab(Urdu)";
            //tbookOptions[3]?.value="/book_publishing/Class 8/Urdu book/MATH-VIII/Maths VII.htm"

        } else if ((tclass.value == 8) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Ruchira";
            tbookOptions[1]?.value = "textbook.htm?hhsk1=0-15"
            //tbookOptions[2]?.text="";
            //tbookOptions[2]?.value=""

        } else if ((tclass.value == 8) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Vasant";
            tbookOptions[1]?.value = "textbook.htm?hhvs1=0-18"
            tbookOptions[2]?.text = "Durva";
            tbookOptions[2]?.value = "textbook.htm?hhdv1=0-19"
            tbookOptions[3]?.text = "Bharat Ki Khoj";
            tbookOptions[3]?.value = "textbook.htm?hhbk1=0-9"
            tbookOptions[4]?.text = "Sanshipt Budhcharit";
            tbookOptions[4]?.value = "textbook.htm?hhsb1=0-5"

        } else if ((tclass.value == 8) && (tSubject.text == "Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Science";
            tbookOptions[1]?.value = "textbook.htm?hesc1=0-18"
            tbookOptions[2]?.text = "Vigyan";
            tbookOptions[2]?.value = "textbook.htm?hhsc1=0-18"
            tbookOptions[3]?.text = "Science(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?huse1=0-18"
            //tbookOptions[4]?.text="Exemplar Problems";
            //tbookOptions[4]?.value="textbook.htm?heep1=0-18"
            //tbookOptions[3]?.text="Science Aur Technology (Urdu)";
            //tbookOptions[3]?.value="/book_publishing/Class 8/Urdu book/science & technology-VIII/science & technology.htm"

        } else if ((tclass.value == 8) && (tSubject.text == "Social Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Resource And Development(Geography)";
            tbookOptions[1]?.value = "textbook.htm?hess4=0-6"
            tbookOptions[2]?.text = "Sansadhan Avam Vikas(Bhugol)";
            tbookOptions[2]?.value = "textbook.htm?hhss4=0-6"
            tbookOptions[3]?.text = "Social And Political Life";
            tbookOptions[3]?.value = "textbook.htm?hess3=0-10"
            tbookOptions[4]?.text = "Samajik Avam Rajnatik Jeevan";
            tbookOptions[4]?.value = "textbook.htm?hhss3=0-10"
            tbookOptions[5]?.text = "Our-Pasts-III  ";
            tbookOptions[5]?.value = "textbook.htm?hess2=0-10"
            tbookOptions[6]?.text = "Hamare Atit-III (Itihas) ";
            tbookOptions[6]?.value = "textbook.htm?hhss1=0-10"
            //tbookOptions[7]?.text="Our-Pasts-III (Part-II) ";
            //tbookOptions[7]?.value="textbook.htm?hess2=0-6"
            //tbookOptions[8]?.text="Hamare Atit-III (Bhag-II) ";
            //tbookOptions[8]?.value="textbook.htm?hhss2=0-6"
            tbookOptions[7]?.text = "Geography(Urdu)";
            tbookOptions[7]?.value = "textbook.htm?hugy1=0-6"
            tbookOptions[8]?.text = "Samaji Aur Siyasi Zindagi(Urdu)";
            tbookOptions[8]?.value = "textbook.htm?huss1=0-10"
            tbookOptions[9]?.text = "Hamare Maazi (Urdu)";
            tbookOptions[9]?.value = "textbook.htm?huhm1=0-10"
            //tbookOptions[10]?.text="Hamare Maazi Part-II(Urdu)";
            //tbookOptions[10]?.value="textbook.htm?huhm2=0-6"


        } else if ((tclass.value == 8) && (tSubject.text == "Environmental Education")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Project Books";
            tbookOptions[1]?.value = "/book_publishing/envedu/8/8.htm"
            tbookOptions[2]?.text = "";
            tbookOptions[2]?.value = ""

        } else if ((tclass.value == 8) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Apni Zaban";
            tbookOptions[1]?.value = "textbook.htm?huaz1=0-22"
            tbookOptions[2]?.text = "Urdu Guldasta (Supl)";
            tbookOptions[2]?.value = "textbook.htm?huug1=0-9"
            tbookOptions[3]?.text = "Door-Pass";
            tbookOptions[3]?.value = "textbook.htm?hudp1=0-20"
            tbookOptions[4]?.text = "Jaan Pahechan";
            tbookOptions[4]?.value = "textbook.htm?hujp1=0-20"


        } else if ((tclass.value == 9) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Beehive English Text Book";
            tbookOptions[1]?.value = "textbook.htm?iebe1=0-11"
            tbookOptions[2]?.text = "Moments Supplimentary Reader";
            tbookOptions[2]?.value = "textbook.htm?iemo1=0-10"
            tbookOptions[3]?.text = "Words and Expressions– 1";
            tbookOptions[3]?.value = "textbook.htm?iewe1=0-11"


        } else if ((tclass.value == 9) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Kshitij Hindi Text Book";
            tbookOptions[1]?.value = "textbook.htm?ihks1=0-17"
            tbookOptions[2]?.text = "Sprash";
            tbookOptions[2]?.value = "textbook.htm?ihsp1=0-13"
            tbookOptions[3]?.text = "Kritika";
            tbookOptions[3]?.value = "textbook.htm?ihkr1=0-5"
            tbookOptions[4]?.text = "Sanchayan";
            tbookOptions[4]?.value = "textbook.htm?ihsa1=0-6"
            //tbookOptions[5]?.text="";
            //tbookOptions[5]?.value=""

        } else if ((tclass.value == 9) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Shemushi Prathmo Bhag";
            tbookOptions[1]?.value = "textbook.htm?ihsh1=0-12"
            tbookOptions[2]?.text = "Vyakaranavithi";
            tbookOptions[2]?.value = "textbook.htm?jhva1=0-14"
            tbookOptions[3]?.text = "Abhyaswaan Bhav";
            tbookOptions[3]?.value = "textbook.htm?isab1=0-12"

        } else if ((tclass.value == 9) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics";
            tbookOptions[1]?.value = "textbook.htm?iemh1=0-15"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?ihmh1=0-15"
            tbookOptions[3]?.text = "Reyazi (Urdu)";
            tbookOptions[3]?.value = "textbook.htm?iumh1=0-15"
            //tbookOptions[4]?.text="Exemplar Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?ieep2=0-16"
            //tbookOptions[5]?.text="Exemplar Problem(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?ihep2=0-16"
            //tbookOptions[6]?.text="";
            //tbookOptions[6]?.value=""

        } else if ((tclass.value == 9) && (tSubject.text == "Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Science";
            tbookOptions[1]?.value = "textbook.htm?iesc1=0-15"
            tbookOptions[2]?.text = "Vigyan";
            tbookOptions[2]?.value = "textbook.htm?ihsc1=0-15"
            tbookOptions[3]?.text = "Science (Urdu)";
            tbookOptions[3]?.value = "textbook.htm?iusc1=0-15"
            //tbookOptions[4]?.text="Exemplar Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?ieep1=0-17"
            //tbookOptions[5]?.text="Exemplar Problem(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?ihep1=0-17"
            //tbookOptions[6]?.text="Lab Manual(English))";
            //tbookOptions[6]?.value="textbook.htm?ielm1=0-5"
            //tbookOptions[7]?.text="Lab Manual(Hindi)";
            //tbookOptions[7]?.value="textbook.htm?ihlm1=0-5"

        } else if ((tclass.value == 9) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Gulzare-e-urdu";
            tbookOptions[1]?.value = "textbook.htm?iugu1=0-10"
            tbookOptions[2]?.text = "Nawa-e-urdu";
            tbookOptions[2]?.value = "textbook.htm?iuna1=0-23"
            tbookOptions[3]?.text = "Jaan Pahechan";
            tbookOptions[3]?.value = "textbook.htm?iujp1=0-23"
            tbookOptions[4]?.text = "Door Pass";
            tbookOptions[4]?.value = "textbook.htm?iudp1=0-23"
            tbookOptions[5]?.text = "Sab Rang";
            tbookOptions[5]?.value = "textbook.htm?iusr1=0-23"
            tbookOptions[6]?.text = "Asnaf-e-Urdu Adab";
            tbookOptions[6]?.value = "textbook.htm?iuau1=0-40"
        } else if ((tclass.value == 9) && (tSubject.text == "Social Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Democratic Politics";
            tbookOptions[1]?.value = "textbook.htm?iess4=0-5"
            tbookOptions[2]?.text = "Loktantrik Rajniti";
            tbookOptions[2]?.value = "textbook.htm?ihss4=0-6"
            tbookOptions[3]?.text = "Contemporary India";
            tbookOptions[3]?.value = "textbook.htm?iess1=0-6"
            tbookOptions[4]?.text = "Samkalin Bharat";
            tbookOptions[4]?.value = "textbook.htm?ihss1=0-6"
            tbookOptions[5]?.text = "Arthashastra";
            tbookOptions[5]?.value = "textbook.htm?ihss2=0-4"
            tbookOptions[6]?.text = "Economics";
            tbookOptions[6]?.value = "textbook.htm?iess2=0-4"
            tbookOptions[7]?.text = "India and the Contempoarary World-I";
            tbookOptions[7]?.value = "textbook.htm?iess3=0-5"
            tbookOptions[8]?.text = "Bharat Aur Samkalin Vishwa-I";
            tbookOptions[8]?.value = "textbook.htm?ihss3=0-5"
            tbookOptions[9]?.text = "Geographia(Urdu)";
            tbookOptions[9]?.value = "textbook.htm?iuge1=0-6"
            tbookOptions[10]?.text = "Jamhuri Syasat(Urdu)";
            tbookOptions[10]?.value = "textbook.htm?iuss4=0-6"
            tbookOptions[11]?.text = "Hindustan Aur Asri Dunia-I(Urdu)";
            tbookOptions[11]?.value = "textbook.htm?iuhi1=0-8"
            tbookOptions[12]?.text = "Aasri Hindustan";
            tbookOptions[12]?.value = "textbook.htm?iuss1=0-6"
            tbookOptions[13]?.text = "Mashiyat";
            tbookOptions[13]?.value = "textbook.htm?iuss2=0-4"


        } else if ((tclass.value == 9) && (tSubject.text == "Environmental Education")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Project Books";
            tbookOptions[1]?.value = "textbook.htm?iepb1=pp-0"
            tbookOptions[2]?.text = "";
            tbookOptions[2]?.value = ""

        } else if ((tclass.value == 9) && (tSubject.text == "Health and Physical Education")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Health and Physical Education";
            tbookOptions[1]?.value = "textbook.htm?iehp1=0-14"
            tbookOptions[2]?.text = "";
            tbookOptions[2]?.value = ""

        } else if ((tclass.value == 9) && (tSubject.text == "Vocational")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Cashier";
            tbookOptions[1]?.value = "textbook.htm?ievc1=0-5"

            tbookOptions[2]?.text = "Store Operations Assistant";
            tbookOptions[2]?.value = "textbook.htm?ieva1=0-4"

            tbookOptions[3]?.text = "Solanceous Crop Cultivator";
            tbookOptions[3]?.value = "textbook.htm?ievs1=0-5"

            tbookOptions[4]?.text = "Assistant Beauty Therapist";
            tbookOptions[4]?.value = "textbook.htm?ievt1=0-3"

            tbookOptions[5]?.text = "Animal Health Workers (Agriculture)";
            tbookOptions[5]?.value = "textbook.htm?ievw1=0-4"

            tbookOptions[6]?.text = "Hand Embroiderer (Addawala)";
            tbookOptions[6]?.value = "textbook.htm?ieve1=0-5"

            tbookOptions[7]?.text = "Hand Embroiderer";
            tbookOptions[7]?.value = "textbook.htm?ievh1=0-5"

            tbookOptions[8]?.text = "Plumber General";
            tbookOptions[8]?.value = "textbook.htm?iepg1=0-5"

            tbookOptions[9]?.text = "IT Domestic Data Entry Operator";
            tbookOptions[9]?.value = "textbook.htm?ieeo1=0-5"

            tbookOptions[10]?.text = "Employability Skill";
            tbookOptions[10]?.value = "textbook.htm?iees1=0-5"

        } else if ((tclass.value == 9) && (tSubject.text == "ICT")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Information and Communication Technology";
            tbookOptions[1]?.value = "textbook.htm?iict1=0-8"


        } else if ((tclass.value == 10) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics";
            tbookOptions[1]?.value = "textbook.htm?jemh1=0-15"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?jhmh1=0-15"
            tbookOptions[3]?.text = "Riyazi";
            tbookOptions[3]?.value = "textbook.htm?jumh1=0-15"
            //tbookOptions[4]?.text="Exemplars Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?jeep2=0-14"
            //tbookOptions[5]?.text="Exemplars Problem(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?jhep2=0-14"


        } else if ((tclass.value == 10) && (tSubject.text == "Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Science";
            tbookOptions[1]?.value = "textbook.htm?jesc1=0-16"
            tbookOptions[2]?.text = "Vigyan";
            tbookOptions[2]?.value = "textbook.htm?jhsc1=0-16"
            tbookOptions[3]?.text = "Science(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?jusc1=0-16"
            //tbookOptions[4]?.text="Exemplar Problem(English)";
            //tbookOptions[4]?.value="textbook.htm?jeep1=0-18"
            //tbookOptions[5]?.text="Exemplar Problem(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?jhep1=0-18"
            //tbookOptions[6]?.text="Lab Manual(English)";
            //tbookOptions[6]?.value="textbook.htm?jelm1=0-6"
            //tbookOptions[7]?.text="Lab Manual(Hindi)";
            //tbookOptions[7]?.value="textbook.htm?jhlm1=0-6"

        } else if ((tclass.value == 10) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Kshitij-2";
            tbookOptions[1]?.value = "textbook.htm?jhks1=0-17"
            tbookOptions[2]?.text = "Sparsh";
            tbookOptions[2]?.value = "textbook.htm?jhsp1=0-17"
            tbookOptions[3]?.text = "Sanchayan Bhag-2";
            tbookOptions[3]?.value = "textbook.htm?jhsy1=0-3"
            tbookOptions[4]?.text = "Kritika";
            tbookOptions[4]?.value = "textbook.htm?jhkr1=0-5"
            //tbookOptions[5]?.text="";
            //tbookOptions[5]?.value=""
        } else if ((tclass.value == 10) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "First Flight";
            tbookOptions[1]?.value = "textbook.htm?jeff1=0-11"
            tbookOptions[2]?.text = "Foot Prints Without feet Supp. Reader";
            tbookOptions[2]?.value = "textbook.htm?jefp1=0-10"
            tbookOptions[3]?.text = "Words and Expressions– 2";
            tbookOptions[3]?.value = "textbook.htm?jewe2=0-11"
            //tbookOptions[3]?.text="";
            //tbookOptions[3]?.value=""

        } else if ((tclass.value == 10) && (tSubject.text == "Social Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Contemporary India ";
            tbookOptions[1]?.value = "textbook.htm?jess1=0-7"
            tbookOptions[2]?.text = "Samkalin Bharat";
            tbookOptions[2]?.value = "textbook.htm?jhss1=0-7"
            tbookOptions[3]?.text = "Aasri Hindustan-II";
            tbookOptions[3]?.value = "textbook.htm?juss1=0-7"
            tbookOptions[4]?.text = "Understanding Economic Development";
            tbookOptions[4]?.value = "textbook.htm?jess2=0-5"
            tbookOptions[5]?.text = "Arthik Vikas ki Samajh";
            tbookOptions[5]?.value = "textbook.htm?jhss2=0-5"
            tbookOptions[6]?.text = "Maashi Taraqqui Ki Samajh";
            tbookOptions[6]?.value = "textbook.htm?juss2=0-5"
            tbookOptions[7]?.text = "India and the Contemporary World-II ";
            tbookOptions[7]?.value = "textbook.htm?jess3=0-5"
            tbookOptions[8]?.text = "Bharat Aur Samakalin Vishav-2";
            tbookOptions[8]?.value = "textbook.htm?jhss3=0-5"
            tbookOptions[9]?.text = "Hindustan Aur Asri Duniya";
            tbookOptions[9]?.value = "textbook.htm?juss3=0-8"
            tbookOptions[10]?.text = "Democratic Politics";
            tbookOptions[10]?.value = "textbook.htm?jess4=0-8"
            tbookOptions[11]?.text = "Loktantrik Rajniti";
            tbookOptions[11]?.value = "textbook.htm?jhss4=0-8"
            tbookOptions[12]?.text = "Jamhuri Siyasat-II ";
            tbookOptions[12]?.value = "textbook.htm?juss4=0-8"


        } else if ((tclass.value == 10) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Shemushi";
            tbookOptions[1]?.value = "textbook.htm?jhsk1=0-12"
            tbookOptions[2]?.text = "Vyakaranavithi";
            tbookOptions[2]?.value = "textbook.htm?jhva1=0-14"
            tbookOptions[3]?.text = "Abhyaswaan Bhav-II";
            tbookOptions[3]?.value = "textbook.htm?jsab1=0-14"


        } else if ((tclass.value == 10) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Gulzar-e-Urdu";
            tbookOptions[1]?.value = "textbook.htm?juge1=0-12"
            tbookOptions[2]?.text = "Nawa-e-Urdu";
            tbookOptions[2]?.value = "textbook.htm?june1=0-11"
            tbookOptions[3]?.text = "Urdu Qwaid aur Insha";
            tbookOptions[3]?.value = "textbook.htm?juuq1=0-22"
            tbookOptions[4]?.text = "Jaan Pahechan";
            tbookOptions[4]?.value = "textbook.htm?jujp1=0-22"
            tbookOptions[5]?.text = "Door-Paas";
            tbookOptions[5]?.value = "textbook.htm?judp1=0-19"
            tbookOptions[6]?.text = "Sab Rang";
            tbookOptions[6]?.value = "textbook.htm?jusr1=0-9"


        } else if ((tclass.value == 10) && (tSubject.text == "Environmental Education")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Project Books";
            tbookOptions[1]?.value = ""
            tbookOptions[2]?.text = "";
            tbookOptions[2]?.value = ""

        } else if ((tclass.value == 11) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Bhaswati";
            tbookOptions[1]?.value = "textbook.htm?khsk1=0-12"
            tbookOptions[2]?.text = "Shashwati";
            tbookOptions[2]?.value = "textbook.htm?khsk2=0-14"


        } else if ((tclass.value == 11) && (tSubject.text == "Accountancy")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Financial Accounting-I";
            tbookOptions[1]?.value = "textbook.htm?keac1=0-8"
            tbookOptions[2]?.text = "Lekhashastra-I";
            tbookOptions[2]?.value = "textbook.htm?khac1=0-8"
            tbookOptions[3]?.text = "Accountancy-II";
            tbookOptions[3]?.value = "textbook.htm?keac2=0-5"
            tbookOptions[4]?.text = "Lekhashastra-II";
            tbookOptions[4]?.value = "textbook.htm?khac2=0-5"
            tbookOptions[5]?.text = "Khatadari-I(Urdu)";
            tbookOptions[5]?.value = "textbook.htm?kuac1=0-8"
            tbookOptions[6]?.text = "Khatadari-II(Urdu)";
            tbookOptions[6]?.value = "textbook.htm?kuac2=0-5"


        } else if ((tclass.value == 11) && (tSubject.text == "Business Studies")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Business Studies";
            tbookOptions[1]?.value = "textbook.htm?kebs1=0-11"
            tbookOptions[2]?.text = "Vyavsay Adhyanan";
            tbookOptions[2]?.value = "textbook.htm?khbs1=0-12"
            tbookOptions[3]?.text = "Karobari Uloom I";
            tbookOptions[3]?.value = "textbook.htm?kubs1=0-12"

        } else if ((tclass.value == 11) && (tSubject.text == "Chemistry")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Chemistry Part-I";
            tbookOptions[1]?.value = "textbook.htm?kech1=0-7"
            tbookOptions[2]?.text = "Rasayan Vigyan bhag-I";
            tbookOptions[2]?.value = "textbook.htm?khch1=0-7"
            tbookOptions[3]?.text = "Keemiya I";
            tbookOptions[3]?.value = "textbook.htm?kuch1=0-7"
            tbookOptions[4]?.text = "Chemistry Part II";
            tbookOptions[4]?.value = "textbook.htm?kech2=0-7"
            tbookOptions[5]?.text = "Rasayan Vigyan bhag-II";
            tbookOptions[5]?.value = "textbook.htm?khch2=0-7"
            tbookOptions[6]?.text = "Keemiya II";
            tbookOptions[6]?.value = "textbook.htm?kuch2=0-7"
            //tbookOptions[7]?.text="Online Chemistry book Part I";
            //tbookOptions[7]?.value="http://www.ncert.nic.in/online_sub_books/chemistry/chem_I_ps.html"
            //tbookOptions[8]?.text="Exemplar Problems(English)";
            //tbookOptions[8]?.value="textbook.htm?keep5=0-15"
            //tbookOptions[9]?.text="Exemplar Problems(Hindi)";
            //tbookOptions[9]?.value="textbook.htm?khep5=0-15"
            //tbookOptions[10]?.text="Lab Manual(English)";
            //tbookOptions[10]?.value="textbook.htm?kelm2=0-8"
            //tbookOptions[11]?.text="Lab Manual(Hindi)";
            //tbookOptions[11]?.value="textbook.htm?khlm2=0-8"


        } else if ((tclass.value == 11) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics";
            tbookOptions[1]?.value = "textbook.htm?kemh1=0-16"
            tbookOptions[2]?.text = "Ganit";
            tbookOptions[2]?.value = "textbook.htm?khmh1=0-16"
            tbookOptions[3]?.text = "Riyazi I";
            tbookOptions[3]?.value = "textbook.htm?kumh1=0-16"
            //tbookOptions[4]?.text="Exemplar Problems(English)";
            //tbookOptions[4]?.value="textbook.htm?keep2=0-18"
            //tbookOptions[5]?.text="Exemplar Problems(Hindi)";
            //tbookOptions[5]?.value="textbook.htm?khep2=0-18"

        } else if ((tclass.value == 11) && (tSubject.text == "Vocational")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Floriculturist- Protected Cultivation";
            tbookOptions[1]?.value = "textbook.htm?kepc1=0-5"
            tbookOptions[2]?.text = "Vision Technician";
            tbookOptions[2]?.value = "textbook.htm?kevt1=0-7"
            tbookOptions[3]?.text = "Floriculturist";
            tbookOptions[3]?.value = "textbook.htm?keoc1=0-6"
            tbookOptions[4]?.text = "General Duty Assistant";
            tbookOptions[4]?.value = "textbook.htm?keda1=0-5"
            tbookOptions[5]?.text = "Dairy Farmer Enterpreneur";
            tbookOptions[5]?.value = "textbook.htm?kedf1=0-4"
            tbookOptions[6]?.text = "Floriculturist";
            tbookOptions[6]?.value = "textbook.htm?kefc1=0-6"

        } else if ((tclass.value == 11) && (tSubject.text == "Statistics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Statistics for Economics";
            tbookOptions[1]?.value = "textbook.htm?kest1=0-9"
            tbookOptions[2]?.text = "Sankhyiki";
            tbookOptions[2]?.value = "textbook.htm?khst1=0-9"
            //tbookOptions[3]?.text="Shumariat bara-e-mushaiat";
            //tbookOptions[3]?.value="/book_publishing/CLASS 11/Urdu Books/Statics for Economics-XI(Shumariat bara-e-mushaiat)/Statics.htm"


        } else if ((tclass.value == 11) && (tSubject.text == "Biology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Biology";
            tbookOptions[1]?.value = "textbook.htm?kebo1=0-22"
            tbookOptions[2]?.text = "Jeev Vigyan";
            tbookOptions[2]?.value = "textbook.htm?khbo1=0-22"
            tbookOptions[3]?.text = "Hayatiyaat";
            tbookOptions[3]?.value = "textbook.htm?kubo1=0-22"
            //tbookOptions[4]?.text="Hayatiyaat II";
            //tbookOptions[4]?.value="textbook.htm?kubo2=0-12"
            //tbookOptions[5]?.text="Human Ecology and Family Sciences Part I ";
            //tbookOptions[5]?.value="textbook.htm?kehe1=0-2"
            //tbookOptions[6]?.text="Human Ecology and Family Sciences Part II ";
            //tbookOptions[6]?.value="textbook.htm?kehe2=0-2"

            //tbookOptions[5]?.text="Exemplar Problems(English)";
            ////tbookOptions[5]?.value="textbook.htm?keep4=0-25"
            //tbookOptions[6]?.text="Exemplar Problems(Hindi)";
            //tbookOptions[6]?.value="textbook.htm?khep4=0-25"
            //tbookOptions[7]?.text="Lab Manual(English)";
            //tbookOptions[7]?.value="textbook.htm?kelm3=0-17"


        } else if ((tclass.value == 11) && (tSubject.text == "Home Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";

            tbookOptions[1]?.text = "Human Ecology and Family Sciences Part I ";
            tbookOptions[1]?.value = "textbook.htm?kehe1=0-2"
            tbookOptions[2]?.text = "Human Ecology and Family Sciences Part II ";
            tbookOptions[2]?.value = "textbook.htm?kehe2=0-2"

            tbookOptions[3]?.text = "Manav Paristhitiki evm pariwar vigyan Bhag-I ";
            tbookOptions[3]?.value = "textbook.htm?khhe1=0-2"

            tbookOptions[4]?.text = "Manav Paristhitiki evm pariwar vigyan Bhag-II ";
            tbookOptions[4]?.value = "textbook.htm?khhe2=0-2"


        } else if ((tclass.value == 11) && (tSubject.text == "Psychology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Introduction to Psychology";
            tbookOptions[1]?.value = "textbook.htm?kepy1=0-9"
            tbookOptions[2]?.text = "Manovigyan";
            tbookOptions[2]?.value = "textbook.htm?khpy1=0-9"
            tbookOptions[3]?.text = "Nafsiyaat ";
            tbookOptions[3]?.value = "textbook.htm?kupy1=0-9"

        } else if ((tclass.value == 11) && (tSubject.text == "Economics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Indian Economic Development";
            tbookOptions[1]?.value = "textbook.htm?keec1=0-10"
            tbookOptions[2]?.text = "Bhartiya Airthryavstha Ka Vikas ";
            tbookOptions[2]?.value = "textbook.htm?khec1=0-10"
            tbookOptions[3]?.text = "Hindustan Ki Moaashi Tarraqqi(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?kuec1=0-10"
            tbookOptions[4]?.text = "Shumariyaat Bar-e-Mushiyat(Urdu)";
            tbookOptions[4]?.value = "textbook.htm?kusc1=0-9"


        } else if ((tclass.value == 11) && (tSubject.text == "Geography")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Fundamental of Physical Geography";
            tbookOptions[1]?.value = "textbook.htm?kegy2=0-16"
            tbookOptions[2]?.text = "Bhautique Bhugol ke Mool Sidhant";
            tbookOptions[2]?.value = "textbook.htm?khgy2=0-16"
            tbookOptions[3]?.text = "Tabai Gugraphiya ke Mubadiyat";
            tbookOptions[3]?.value = "textbook.htm?kugy2=0-16"
            tbookOptions[4]?.text = "Hindustan Tabiee Mahol (Urdu)";
            tbookOptions[4]?.value = "textbook.htm?kugy1=0-8"
            tbookOptions[5]?.text = "Geographia mein amli kaam (Urdu)";
            tbookOptions[5]?.value = "textbook.htm?kugy1=0-8"
            tbookOptions[6]?.text = "Pratical Work in Geography";
            tbookOptions[6]?.value = "textbook.htm?kegy3=0-8"
            tbookOptions[7]?.text = "Bhugol Main Prayogatmak Karya";
            tbookOptions[7]?.value = "textbook.htm?khgy3=0-8"
            tbookOptions[8]?.text = "India Physical Environment";
            tbookOptions[8]?.value = "textbook.htm?kegy1=0-7"
            tbookOptions[9]?.text = "Bhart Bhautik Paryabaran";
            tbookOptions[9]?.value = "textbook.htm?khgy1=0-7"
            tbookOptions[10]?.text = "Jughrafia Mein Aamli Kam";
            tbookOptions[10]?.value = "textbook.htm?kugy3=0-8"
            tbookOptions[11]?.text = "Geographia Ke Mubadiyat";
            tbookOptions[11]?.value = "textbook.htm?kugm1=0-16"


        } else if ((tclass.value == 11) && (tSubject.text == "Physics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Physics Part-I";
            tbookOptions[1]?.value = "textbook.htm?keph1=0-8"
            tbookOptions[2]?.text = "Bhautiki-I";
            tbookOptions[2]?.value = "textbook.htm?khph1=0-8"
            tbookOptions[3]?.text = "Tabiyaat-I";
            tbookOptions[3]?.value = "textbook.htm?kuph1=0-8"
            tbookOptions[4]?.text = "Physics Part-II";
            tbookOptions[4]?.value = "textbook.htm?keph2=0-7"
            tbookOptions[5]?.text = "Bhautiki-II";
            tbookOptions[5]?.value = "textbook.htm?khph2=0-7"
            tbookOptions[6]?.text = "Tabiyaat-II";
            tbookOptions[6]?.value = "textbook.htm?kuph2=0-7"
            //tbookOptions[7]?.text="Exemplar Problems(English)";
            //tbookOptions[7]?.value="textbook.htm?keep3=0-17"
            //tbookOptions[8]?.text="Exemplar Problems(Hindi)";
            //tbookOptions[8]?.value="textbook.htm?khep3=0-17"
            //tbookOptions[9]?.text="Lab Manual(English)";
            //tbookOptions[9]?.value="textbook.htm?kelm1=0-14"


        } else if ((tclass.value == 11) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Antra";
            tbookOptions[1]?.value = "textbook.htm?khat1=0-19"
            tbookOptions[2]?.text = "Aroh";
            tbookOptions[2]?.value = "textbook.htm?khar1=0-20"
            //tbookOptions[3]?.text="Vyavaharik Hindi";
            //tbookOptions[3]?.value="/book_publishing/CLASS 11/Viyabharik Hindi11/ch11_vyawaharik_hindi.htm"
            tbookOptions[3]?.text = "Vitan";
            tbookOptions[3]?.value = "textbook.htm?khvt1=0-4"
            tbookOptions[4]?.text = "Antral";
            tbookOptions[4]?.value = "textbook.htm?khan1=0-3"
            //tbookOptions[6]?.text="Abhivyakti Aur Madhyam";
            //tbookOptions[6]?.value="textbook.htm?lham1=0-18"
        } else if ((tclass.value == 11) && (tSubject.text == "Sociology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Introducing Sociology";
            tbookOptions[1]?.value = "textbook.htm?kesy1=0-5"
            tbookOptions[2]?.text = "Samajshastra-I";
            tbookOptions[2]?.value = "textbook.htm?khsy1=0-5"
            tbookOptions[3]?.text = "Samajiyaat Ka Tarf";
            tbookOptions[3]?.value = "textbook.htm?kusy1=0-5"
            tbookOptions[4]?.text = "Understanding Society";
            tbookOptions[4]?.value = "textbook.htm?kesy2=0-5"
            tbookOptions[5]?.text = "Samaj ka Bodh";
            tbookOptions[5]?.value = "textbook.htm?khsy2=0-5"
            tbookOptions[6]?.text = "Mutala-e-Muashira";
            tbookOptions[6]?.value = "textbook.htm?kusy2=0-5"

        } else if ((tclass.value == 11) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Woven Words";
            tbookOptions[1]?.value = "textbook.htm?keww1=0-27"
            tbookOptions[2]?.text = "Hornbill";
            tbookOptions[2]?.value = "textbook.htm?kehb1=0-14"
            tbookOptions[3]?.text = "Snapshots Suppl.Reader English";
            tbookOptions[3]?.value = "textbook.htm?kesp1=0-8"


        } else if ((tclass.value == 11) && (tSubject.text == "Political Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Political Theory";
            tbookOptions[1]?.value = "textbook.htm?keps1=0-10"
            tbookOptions[2]?.text = "Raajneeti Sidhant";
            tbookOptions[2]?.value = "textbook.htm?khps1=0-10"
            tbookOptions[3]?.text = "Hindustani Aain aur Kaam";
            tbookOptions[3]?.value = "textbook.htm?kups1=0-10"
            tbookOptions[4]?.text = "Indian Economic Development(Urdu)";
            tbookOptions[4]?.value = "textbook.htm?kuec1=0-10"
            tbookOptions[5]?.text = "India Constitution at Work";
            tbookOptions[5]?.value = "textbook.htm?keps2=0-10"
            tbookOptions[6]?.text = "Bharat ka Samvidhan Sidhant aur Vyavhar";
            tbookOptions[6]?.value = "textbook.htm?khps2=0-10"
            tbookOptions[7]?.text = "Siyasi Nazaria";
            tbookOptions[7]?.value = "textbook.htm?kups2=0-10"
        } else if ((tclass.value == 11) && (tSubject.text == "History")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Themes in World History";
            tbookOptions[1]?.value = "textbook.htm?kehs1=0-4"
            tbookOptions[2]?.text = "Vishwa Itihas Ke Kuch Vishay";
            tbookOptions[2]?.value = "textbook.htm?khhs1=0-11"
            tbookOptions[3]?.text = "Tareekh-e-Alam per Mabni Mauzuaat Part I";
            tbookOptions[3]?.value = "textbook.htm?kuta1=0-11"


        } else if ((tclass.value == 11) && (tSubject.text == "Heritage Crafts")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Living Craft Traditions of India";
            tbookOptions[1]?.value = "textbook.htm?kehc1=0-10"
            tbookOptions[2]?.text = "Hindustan me Dastkari Ki Riwayat";
            tbookOptions[2]?.value = "textbook.htm?kuhc1=0-10"
            tbookOptions[3]?.text = "Dastkari";
            tbookOptions[3]?.value = "textbook.htm?kuhc2=0-10"

        } else if ((tclass.value == 11) && (tSubject.text == "Graphics design")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "The story of graphic design";
            tbookOptions[1]?.value = "textbook.htm?kegd1=0-8"
            tbookOptions[2]?.text = "graphic design ek kahani";
            tbookOptions[2]?.value = "textbook.htm?khgd1=0-8"

        } else if ((tclass.value == 11) && (tSubject.text == "Computers and Communication Technology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "CCT Part-I";
            tbookOptions[1]?.value = "textbook.htm?kect1=0-8"
            tbookOptions[2]?.text = "CCT Part-II";
            tbookOptions[2]?.value = "textbook.htm?kect2=0-6"
            tbookOptions[3]?.text = "Computer aur Sanchar Prodhogiki Part-I";
            tbookOptions[3]?.value = "textbook.htm?khct1=0-8"
            tbookOptions[4]?.text = "Computer aur Sanchar Prodhogiki Part-II";
            tbookOptions[4]?.value = "textbook.htm?khct2=0-6"
            tbookOptions[5]?.text = "Computer Aur Muwaslati Technology I";
            tbookOptions[5]?.value = "textbook.htm?kuct1=0-8"
            tbookOptions[6]?.text = "Computer Aur Muwaslati Technology II";
            tbookOptions[6]?.value = "textbook.htm?kuct2=0-6"
        } else if ((tclass.value == 11) && (tSubject.text == "Fine Art")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "An Introduction to Indian Art Part-I";
            tbookOptions[1]?.value = "textbook.htm?kefa1=0-8"
            tbookOptions[2]?.text = "Bhartiya Kala ka parichay";
            tbookOptions[2]?.value = "textbook.htm?khfa1=0-9"

        } else if ((tclass.value == 11) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Nai Awaz";
            tbookOptions[1]?.value = "textbook.htm?kuna1=0-20"
            tbookOptions[2]?.text = "Dhanak";
            tbookOptions[2]?.value = "textbook.htm?kudh1=0-27"
            tbookOptions[3]?.text = "Gulistan e Adab";
            tbookOptions[3]?.value = "textbook.htm?kuga1=0-33"
            tbookOptions[4]?.text = "Khyabane Urdu";
            tbookOptions[4]?.value = "textbook.htm?kuku1=0-15"
        } else if ((tclass.value == 11) && (tSubject.text == "Creative Writing and Translation")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Srijan";
            tbookOptions[1]?.value = "textbook.htm?khsr1=0-4"

            tbookOptions[2]?.text = "Takhleequi Jauhar";
            tbookOptions[2]?.value = "textbook.htm?kucw1=0-4"

        } else if ((tclass.value == 11) && (tSubject.text == "Informatics Practices")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Informatics Practices";
            tbookOptions[1]?.value = "textbook.htm?keip1=0-8"


        } else if ((tclass.value == 11) && (tSubject.text == "Computer Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Computer Science";
            tbookOptions[1]?.value = "textbook.htm?kecs1=0-11"


        } else if ((tclass.value == 12) && (tSubject.text == "Accountancy")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Accountancy-I";
            tbookOptions[1]?.value = "textbook.htm?leac1=0-5"
            tbookOptions[2]?.text = "Accountancy Part-II";
            tbookOptions[2]?.value = "textbook.htm?leac2=0-6"
            tbookOptions[3]?.text = "Lekhashastra Part-I";
            tbookOptions[3]?.value = "textbook.htm?lhac1=0-5"
            tbookOptions[4]?.text = "Lekhashastra Part-II";
            tbookOptions[4]?.value = "textbook.htm?lhac2=0-6"
            tbookOptions[5]?.text = "Computerised Accounting System";
            tbookOptions[5]?.value = "textbook.htm?leca1=0-6"
            tbookOptions[6]?.text = "Khatadari-I(Urdu)";
            tbookOptions[6]?.value = "textbook.htm?luac1=0-5"
            tbookOptions[7]?.text = "Khatadari-II(Urdu)";
            tbookOptions[7]?.value = "textbook.htm?luac2=0-12"
        } else if ((tclass.value == 12) && (tSubject.text == "Mathematics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Mathematics Part-I";
            tbookOptions[1]?.value = "textbook.htm?lemh1=0-6"
            tbookOptions[2]?.text = "Mathematics Part-II";
            tbookOptions[2]?.value = "textbook.htm?lemh2=0-7"
            tbookOptions[3]?.text = "Ganit-I";
            tbookOptions[3]?.value = "textbook.htm?lhmh1=0-6"
            tbookOptions[4]?.text = "Ganit-II";
            tbookOptions[4]?.value = "textbook.htm?lhmh2=0-7"
            tbookOptions[5]?.text = "Riyazi-I";
            tbookOptions[5]?.value = "textbook.htm?lumh1=0-6"
            tbookOptions[6]?.text = "Riyazi-II";
            tbookOptions[6]?.value = "textbook.htm?lumh2=0-7"

            //tbookOptions[7]?.text="Exemplar Problems(English)";
            //tbookOptions[7]?.value="textbook.htm?leep2=0-16"
            //tbookOptions[8]?.text="Exemplar Problems(Hindi)";
            //tbookOptions[8]?.value="textbook.htm?lhep2=0-15"


        } else if ((tclass.value == 12) && (tSubject.text == "Physics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Physics Part-I";
            tbookOptions[1]?.value = "textbook.htm?leph1=0-8"
            tbookOptions[2]?.text = "Physics Part-II";
            tbookOptions[2]?.value = "textbook.htm?leph2=0-7"
            tbookOptions[3]?.text = "Bhautiki-I";
            tbookOptions[3]?.value = "textbook.htm?lhph1=0-8"
            tbookOptions[4]?.text = "Bhautiki-II";
            tbookOptions[4]?.value = "textbook.htm?lhph2=0-7"
            tbookOptions[5]?.text = "Tabiyaat-I";
            tbookOptions[5]?.value = "textbook.htm?luph1=0-8"
            tbookOptions[6]?.text = "Tabiyaat-II";
            tbookOptions[6]?.value = "textbook.htm?luph2=0-7"
            //tbookOptions[7]?.text="Examplar Prolems(English)";
            //	tbookOptions[7]?.value="textbook.htm?leep6=0-17"
            //tbookOptions[8]?.text="Examplar Problems(Hindi)";
            //tbookOptions[8]?.value="textbook.htm?lhep6=0-17"
            //tbookOptions[9]?.text="Lab Manual(English)";
            //tbookOptions[9]?.value="textbook.htm?lelm3=0-7"
            //tbookOptions[10]?.text="Lab Manual(Hindi)";
            //tbookOptions[10]?.value="textbook.htm?lhlm3=0-16"

        } else if ((tclass.value == 12) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Antra";
            tbookOptions[1]?.value = "textbook.htm?lhat1=0-21"
            tbookOptions[2]?.text = "Aroh";
            tbookOptions[2]?.value = "textbook.htm?lhar1=0-18"
            tbookOptions[3]?.text = "Vitan";
            tbookOptions[3]?.value = "textbook.htm?lhvt1=0-4"
            //tbookOptions[4]?.text="Abhivyakti Aur Madhyam";
            //tbookOptions[4]?.value="textbook.htm?lham1=0-18"
            tbookOptions[4]?.text = "Antral Bhag 2";
            tbookOptions[4]?.value = "textbook.htm?lhan1=0-4"


        } else if ((tclass.value == 12) && (tSubject.text == "English")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Kaliedoscope";
            tbookOptions[1]?.value = "textbook.htm?lekl1=0-21"
            tbookOptions[2]?.text = "Flamingo";
            tbookOptions[2]?.value = "textbook.htm?lefl1=0-14"
            tbookOptions[3]?.text = "Vistas";
            tbookOptions[3]?.value = "textbook.htm?levt1=0-8"


        } else if ((tclass.value == 12) && (tSubject.text == "Biology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Biology";
            tbookOptions[1]?.value = "textbook.htm?lebo1=0-16"
            tbookOptions[2]?.text = "Jeev Vigyan";
            tbookOptions[2]?.value = "textbook.htm?lhbo1=0-16"
            tbookOptions[3]?.text = "Human Ecology and Family Sciences Part I ";
            tbookOptions[3]?.value = "textbook.htm?lehe1=0-10"
            tbookOptions[4]?.text = "Hayatiyaat";
            tbookOptions[4]?.value = "textbook.htm?lubo1=0-16"
            //tbookOptions[4]?.text="Exemplar Problems";
            //tbookOptions[4]?.value="textbook.htm?leep4=0-19"
            //tbookOptions[5]?.text="Lab Manual(English)";
            //tbookOptions[5]?.value="textbook.htm?lelm2=0-16"

        } else if ((tclass.value == 12) && (tSubject.text == "History")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Themes in Indian History-I";
            tbookOptions[1]?.value = "textbook.htm?lehs1=0-4"
            tbookOptions[2]?.text = "Bharatiya Itihas ke kuchh Vishay-I";
            tbookOptions[2]?.value = "textbook.htm?lhhs1=0-4"
            tbookOptions[3]?.text = "Themes in Indian History-II";
            tbookOptions[3]?.value = "textbook.htm?lehs2=0-5"
            tbookOptions[4]?.text = "Bharatiya Itihas ke kuchh Vishay-II ";
            tbookOptions[4]?.value = "textbook.htm?lhhs2=0-5"
            tbookOptions[5]?.text = "Themes in Indian History-III";
            tbookOptions[5]?.value = "textbook.htm?lehs3=0-6"
            tbookOptions[6]?.text = "Bharatiya Itihas ke kuchh Vishay-III";
            tbookOptions[6]?.value = "textbook.htm?lhhs3=0-6"
            tbookOptions[7]?.text = "Tareekh-e-Hind ke Mauzuaat-I(Urdu)";
            tbookOptions[7]?.value = "textbook.htm?luth1=0-4"
            tbookOptions[8]?.text = "Tareekh-e-Hind ke Mauzuaat-II(Urdu)";
            tbookOptions[8]?.value = "textbook.htm?luth2=0-5"
            tbookOptions[9]?.text = "Tareekh-e-Hind ke Mauzuaat-III(Urdu)";
            tbookOptions[9]?.value = "textbook.htm?luth3=0-6"

        } else if ((tclass.value == 12) && (tSubject.text == "Geography")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Fundamentals of Human Geography";
            tbookOptions[1]?.value = "textbook.htm?legy1=0-10"
            tbookOptions[2]?.text = "Practical Work in Geography Part II";
            tbookOptions[2]?.value = "textbook.htm?legy3=0-6"
            tbookOptions[3]?.text = "Manav Bhugol Ke Mool Sidhant";
            tbookOptions[3]?.value = "textbook.htm?lhgy1=0-10"
            tbookOptions[4]?.text = "Bhugol main peryojnatmak pryogatmak karye";
            tbookOptions[4]?.value = "textbook.htm?lhgy3=0-6"
            tbookOptions[5]?.text = "India -People And Economy";
            tbookOptions[5]?.value = "textbook.htm?legy2=0-12"
            tbookOptions[6]?.text = "Bharat log aur arthvyasastha(Bhugol)";
            tbookOptions[6]?.value = "textbook.htm?lhgy2=0-12"
            tbookOptions[7]?.text = "Insani Jughrafia Ke Buniyadi Usool(Urdu)";
            tbookOptions[7]?.value = "textbook.htm?lufh1=0-10"
            tbookOptions[8]?.text = " Hindustan Awam Aur Maishat(Urdu)";
            tbookOptions[8]?.value = "textbook.htm?lugy1=0-12"
            tbookOptions[9]?.text = " Jughrafia Mein Aamli Kam(Urdu)";
            tbookOptions[9]?.value = "textbook.htm?lugy3=0-6"

        } else if ((tclass.value == 12) && (tSubject.text == "Psychology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Psychology";
            tbookOptions[1]?.value = "textbook.htm?lepy1=0-9"
            tbookOptions[2]?.text = "Manovigyan";
            tbookOptions[2]?.value = "textbook.htm?lhpy1=0-9"
            tbookOptions[3]?.text = "Nafsiat(Urdu)";
            tbookOptions[3]?.value = "textbook.htm?lupy1=0-9"


        } else if ((tclass.value == 12) && (tSubject.text == "Sociology")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Indian Society";
            tbookOptions[1]?.value = "textbook.htm?lesy1=0-7"
            tbookOptions[2]?.text = "Bhartiya Samaj";
            tbookOptions[2]?.value = "textbook.htm?lhsy1=0-7"
            tbookOptions[3]?.text = "Social Change and Development in India";
            tbookOptions[3]?.value = "textbook.htm?lesy2=0-8"
            tbookOptions[4]?.text = "Bharat main Samajik Parivartan aur Vikas";
            tbookOptions[4]?.value = "textbook.htm?lhsy2=0-8"
            tbookOptions[5]?.text = "Hindustani Samaj(Urdu)";
            tbookOptions[5]?.value = "textbook.htm?luis1=0-7"
            tbookOptions[6]?.text = "Hindustan Mein Samaji Tabdili Aur Taraqqi(Urdu)";
            tbookOptions[6]?.value = "textbook.htm?lusy2=0-8"


        } else if ((tclass.value == 12) && (tSubject.text == "Chemistry")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Chemistry-I";
            tbookOptions[1]?.value = "textbook.htm?lech1=0-9"
            tbookOptions[2]?.text = "Chemistry-II";
            tbookOptions[2]?.value = "textbook.htm?lech2=0-7"
            tbookOptions[3]?.text = "Rasayan vigyan bhag I";
            tbookOptions[3]?.value = "textbook.htm?lhch1=0-9"
            tbookOptions[4]?.text = "Rasayan vigyan bhag II";
            tbookOptions[4]?.value = "textbook.htm?lhch2=0-7"
            //tbookOptions[5]?.text="Exemplar Problems";
            //tbookOptions[5]?.value="textbook.htm?leep5=0-18"
            //tbookOptions[6]?.text="Exemplar Problems(Hindi)";
            //tbookOptions[6]?.value="textbook.htm?lhep4=0-17"
            //tbookOptions[7]?.text="Lab Manual(English)";
            //tbookOptions[7]?.value="textbook.htm?lelm1=0-11"
            //tbookOptions[8]?.text="Lab Manual(Hindi)";
            //tbookOptions[8]?.value="textbook.htm?lhlm1=0-11"
            tbookOptions[5]?.text = "Keemiya-I";
            tbookOptions[5]?.value = "textbook.htm?luch1=0-9"
            tbookOptions[6]?.text = "Keemiya-II";
            tbookOptions[6]?.value = "textbook.htm?luch2=0-7"


        } else if ((tclass.value == 12) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Bhaswati";
            tbookOptions[1]?.value = "textbook.htm?lhsk1=0-12"
            tbookOptions[2]?.text = "Shaswati";
            tbookOptions[2]?.value = "textbook.htm?lhsk2=0-14"
            tbookOptions[3]?.text = "";
            tbookOptions[3]?.value = ""

        } else if ((tclass.value == 12) && (tSubject.text == "Political Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Contemporary World Politics";
            tbookOptions[1]?.value = "textbook.htm?leps1=0-9"
            tbookOptions[2]?.text = "Samkalin Vishwa Rajniti";
            tbookOptions[2]?.value = "textbook.htm?lhps1=0-9"
            tbookOptions[3]?.text = "Political Science-II";
            tbookOptions[3]?.value = "textbook.htm?leps2=0-9"
            tbookOptions[4]?.text = "Swatantra Bharat Mein Rajniti-II";
            tbookOptions[4]?.value = "textbook.htm?lhps2=0-9"
            tbookOptions[5]?.text = "Aasri Alami Siyasat(Urdu)";
            tbookOptions[5]?.value = "textbook.htm?lups1=0-9"
            tbookOptions[6]?.text = "Azadi Ke Baad Hindustan Ki Siyasat(Urdu)";
            tbookOptions[6]?.value = "textbook.htm?luab1=0-9"
        } else if ((tclass.value == 12) && (tSubject.text == "Home Science")) {
            tbookOptions[0]?.text = "..Select Book Title..";

            tbookOptions[1]?.text = "Human Ecology and Family Sciences Part I ";
            tbookOptions[1]?.value = "textbook.htm?lehe1=0-10"
            tbookOptions[2]?.text = "Human Ecology and Family Sciences Part II ";
            tbookOptions[2]?.value = "textbook.htm?lehe2=0-15"

            tbookOptions[3]?.text = "Manav Paristhitik avam Parivar Vigyan Bhag 1 ";
            tbookOptions[3]?.value = "textbook.htm?lehh1=0-10"
            tbookOptions[4]?.text = "Manav Paristhitiki avam Parivar Vigyan Bhag 2";
            tbookOptions[4]?.value = "textbook.htm?lehh2=0-15"


        } else if ((tclass.value == 12) && (tSubject.text == "Economics")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Introductory Microeconomics";
            tbookOptions[1]?.value = "textbook.htm?leec2=0-6"
            tbookOptions[2]?.text = "Introductory Macroeconomics";
            tbookOptions[2]?.value = "textbook.htm?leec1=0-6"
            tbookOptions[3]?.text = "Vyashthi Arthshasrta";
            tbookOptions[3]?.value = "textbook.htm?lhec2=0-6"
            tbookOptions[4]?.text = "Samashty Arthshastra Ek Parichay";
            tbookOptions[4]?.value = "textbook.htm?lhec1=0-6"
            tbookOptions[5]?.text = "Juzvi Maashiyat ka Taruf(Urdu)";
            tbookOptions[5]?.value = "textbook.htm?lume1=0-6"
            tbookOptions[6]?.text = "Kulli Maashiyat Ka Taruf(Urdu)";
            tbookOptions[6]?.value = "textbook.htm?lume2=0-6"

        } else if ((tclass.value == 12) && (tSubject.text == "Business Studies")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Bussiness Studies-I";
            tbookOptions[1]?.value = "textbook.htm?lebs1=0-8"
            tbookOptions[2]?.text = "Vyavasai Adhyan-I";
            tbookOptions[2]?.value = "textbook.htm?lhbs1=0-8"
            tbookOptions[3]?.text = "Bussiness Studies-II";
            tbookOptions[3]?.value = "textbook.htm?lebs2=0-4"
            tbookOptions[4]?.text = "Vyavasai Adhyan-II";
            tbookOptions[4]?.value = "textbook.htm?lhbs2=0-4"
            tbookOptions[5]?.text = "Karobari Uloom I(Urdu)";
            tbookOptions[5]?.value = "textbook.htm?lubs1=0-8"
            tbookOptions[6]?.text = "Karobari Uloom II(Urdu)";
            tbookOptions[6]?.value = "textbook.htm?lubs2=0-5"
        } else if ((tclass.value == 12) && (tSubject.text == "Urdu")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Gulistan-e- Adab";
            tbookOptions[1]?.value = "textbook.htm?luga1=0-11"
            tbookOptions[2]?.text = "Khayaban-e-Urdu";
            tbookOptions[2]?.value = "textbook.htm?luku1=0-6"
            tbookOptions[3]?.text = "Nai Awaz";
            tbookOptions[3]?.value = "textbook.htm?luna1=0-16"
            tbookOptions[4]?.text = "Dhanak";
            tbookOptions[4]?.value = "textbook.htm?ludh1=0-11"


        } else if ((tclass.value == 12) && (tSubject.text == "Heritage Crafts")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            //tbookOptions[1]?.text="Craft Tradition Of India";
            //tbookOptions[1]?.value="textbook.htm?lehc1=0-9"
            tbookOptions[1]?.text = "Hindustan me Dastkari Ki Riwayat";
            tbookOptions[1]?.value = "textbook.htm?luhc1=0-9"
            tbookOptions[2]?.text = "Craft Tradition of India";
            tbookOptions[2]?.value = "textbook.htm?lehc1=0-9"
            tbookOptions[3]?.text = "Bharatiya Hastkla Ki Paramparayen";
            tbookOptions[3]?.value = "textbook.htm?lhhc1=0-9"


        } else if ((tclass.value == 12) && (tSubject.text == "New Age Graphics Design")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "New Age Graphics Design";
            tbookOptions[1]?.value = "textbook.htm?legd1=0-12"


        } else if ((tclass.value == 13) && (tSubject.text == "Hindi")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Abhivyakti Aur Madhyam";
            tbookOptions[1]?.value = "textbook.htm?kham1=0-16"


        } else if ((tclass.value == 13) && (tSubject.text == "Sanskrit")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Sanskrit Sahitya parichay";
            tbookOptions[1]?.value = "textbook.htm?klss1=0-12"


        } else if ((tclass.value == 13) && (tSubject.text == "Heritage Crafts")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Exploring Craft Tradition of India";
            tbookOptions[1]?.value = "textbook.htm?mehc1=0-10"
            tbookOptions[2]?.text = "Bhartiya Hastkala Parmparaon ki Khoj";
            tbookOptions[2]?.value = "textbook.htm?khhc1=0-10"


        } else if ((tclass.value == 14) && (tSubject.text == "Vocational")) {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "Organic Farming";
            tbookOptions[1]?.value = "textbook.htm?geof1=0-5"


        } else if (tSubject.text == "..Select Subject..") {
            tbookOptions[0]?.text = "..Select Book Title..";
            tbookOptions[1]?.text = "";
        }
    }
}

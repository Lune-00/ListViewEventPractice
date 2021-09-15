package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.datas.StudentData

class StudentAdapter(
//    어댑터가 받아야 할 조건.
//    어디에 나타낼 것인가(Context), 어떤 틀로 쓸 것인가(resId), 어떤 리스트의 정보를 뿌릴 것인가(List)
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<StudentData>
) : ArrayAdapter<StudentData>(mContext, resId, mList){

//    화면에 보이는 만큼만 로딩하고 새 화면이 등장할 때마다 resId를 돌려막기해서 데이터를 줄이기 위한 inflate 함수!

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var temprow = convertView

//        convertView가 비었을 때만 데이터를 불러오기 위한 작업(temprow == null)

        if (temprow == null){

//            root 란 무엇인가

            temprow = inf.inflate(R.layout.student_list_item, null)
        }

//        왜 바로 row로 대입하지 않고 temprow에 대입해서 row = temprow 라는 공식을 추가하는 걸까
//        변화 가능한 var 변수의 특성을 변하지 않는 변수 val에 담아주기 위해서?

        val row = temprow!!


        val studentData = mList[position]

//        리스트 xml에서 정보를 가져와서 변수에 담는 과정

        val studentName = row.findViewById<TextView>(R.id.studentName)
        val studentBirthYear = row.findViewById<TextView>(R.id.studentBirthYear)

//        변수의 실제 텍스트를 리스트에 담긴 정보로 나타내주기 위한 작업

        studentName.text = studentData.name

        val koreanAge = 2021 - studentData.birthYear +1

        studentBirthYear.text = "(${koreanAge})세"


        return row
    }

}
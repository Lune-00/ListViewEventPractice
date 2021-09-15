package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.adapters.StudentAdapter
import com.example.myapplication.datas.StudentData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    리스트에 담길 정보를 넣어주기 위한 작업.

    val mStudentList = ArrayList<StudentData>()

//    사전에 lateinit var 작업이 왜 필요한지는 잘 모르겠다.
//    mAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList) 이 공식이
//    val 로 들어갈 수 없어서 한번 더 작업을 하는 것일 텐데 왜 val로 처리하면 안 될까.

    lateinit var mAdapter : StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        정보를 수동으로 넣기 위한 작업.
//        서버에서 받으면 add 뒤에 서버 정보가 들어가거나 add가 아닌 다른 작업을 해야 할 듯.

        mStudentList.add(StudentData("커피콩", 2020))
        mStudentList.add(StudentData("조개빵", 2020))
        mStudentList.add(StudentData("커피콩빵", 2021))
        mStudentList.add(StudentData("크레페", 2021))
        mStudentList.add(StudentData("콩빵", 2020))
        mStudentList.add(StudentData("라즈베리", 2021))
        mStudentList.add(StudentData("식쿠식물", 2020))


//      어디에 보여줄 것인가, 어떤 틀로 보여줄 것인가, 들어갈 정보가 담긴 리스트는 무엇인가.
//      어댑터 클래스에서 요구했던 세 가지 조건을 기입 (Context, resID, List)

        mAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList)

        studentListView.adapter = mAdapter


        studentListView.setOnItemClickListener { adapterView, view, position, id ->

//          몇번째 칸이 눌렸는지 인식하기 위한 작업.
//          position 함수의 역할을 확실히 이해는 못 하겠음. 그냥 0부터 순서대로 위치를 잡아주는 건가?

            val clickedStudent = mStudentList[position]

            Toast.makeText(this, "${clickedStudent.name}이 클릭됨", Toast.LENGTH_SHORT).show()

        }
        
        studentListView.setOnItemLongClickListener { adapterView, view, position, id ->

            val alert = AlertDialog.Builder(this)
            alert.setTitle("삭제 확인")
            alert.setMessage("삭제하시겠습니까?")
//            setPositiveButton 두번째 조건이 뭐냐 대체...{mStudentList.removeAt(position)} 이걸
//            넣어야 할 텐데 어떤 방식으로 넣어야 맞지. listener면 리무브해주는 함수가 들어가야 하지 않나?
            alert.setPositiveButton("삭제", ???)
            alert.setNegativeButton("취소", null)
            alert.show()

//          어댑터에 추가작업 한 후 꼭 넣어주기

            mAdapter.notifyDataSetChanged()

            return@setOnItemLongClickListener true

        }

    }
}
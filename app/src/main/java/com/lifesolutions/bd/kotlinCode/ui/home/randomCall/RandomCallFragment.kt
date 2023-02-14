package com.lifesolutions.bd.kotlinCode.ui.home.randomCall

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.lifesolutions.bd.Adapters.CourseAdapterJava
import com.lifesolutions.bd.Agora.AgoraRandomVoiceCallActivity
import com.lifesolutions.bd.kotlinCode.data.model.*
import com.lifesolutions.bd.kotlinCode.ui.activities.AllActiverUserActivity
import com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter.*
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.random_call_fragment.*

const val TOPIC = "StarnoteRandomCall"

class RandomCallFragment : Fragment() {

    var adapter: CourseAdapterJava? = null


    private var firstName: String? = null
    private var lastName: kotlin.String? = null


    private val TAG = "RandomCallFragment"

    private lateinit var viewModel: RandomCallViewModel

    // Firebase DB
    private lateinit var database: FirebaseDatabase

    // User Info..
    private var authId: String? = null
    private lateinit var animatedLoading: AnimatedLoading

    private var uId:String=FirebaseAuth.getInstance().currentUser?.uid.toString()

    // Realtime Data
    private lateinit var activeUserRef: DatabaseReference
    private var listenerActiveList: ValueEventListener? = null

    private var allCourseUrl: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.lifesolutions.bd.R.layout.random_call_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RandomCallViewModel::class.java)

/*        val courseLayoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val courseViewPool = RecyclerView.RecycledViewPool()

        rv_course_list?.apply {
            layoutManager = courseLayoutManager
            setHasFixedSize(true)
            setRecycledViewPool(courseViewPool)
        }*/

       // getAllCourses()


        animatedLoading = AnimatedLoading(requireContext())
        // NetworkLiveState.init(requireContext())

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid




        // Database Ref
        activeUserRef = database.getReference("ActiveNow")

        // Subscribes
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

//        NetworkLiveState.observe(viewLifecycleOwner, Observer { isConnected ->
//            if (isConnected) {
//                Toast.makeText(requireContext(), "Internet Connected", Toast.LENGTH_SHORT).show()
//                // FirebaseData Retrieve Methods
//                getActiveUserLists()
//                getTeachersLists()
//                getAmbassadorsLists()
//                getCoursesLists()
//            } else {
//                Toast.makeText(requireContext(), "Internet Disconnected", Toast.LENGTH_SHORT).show()
//            }
//        })


        // FirebaseData Retrieve Methods
        getActiveUserLists()
//        getTeachersLists()
//        getAmbassadorsLists()
//        getCampusAmbassadorsLists()
//        //getCoursesLists()
//
//        getTeamMembers()

        // OnClick...

//Social Media Link

    /*    facebookLink.setOnClickListener {



            val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse("facebook.com/starnotesocial")
            startActivity(defaultBrowser)


        }
        youtubeLink.setOnClickListener {

            val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse("youtube.com/c/StarnoteSocial")
            startActivity(defaultBrowser)
        }
        instagramLink.setOnClickListener {

            val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse("Instagram.com/starnotesocial")
            startActivity(defaultBrowser)


        }
*/



//===================================Demo========================






        tv_view_all_active_list?.setOnClickListener {
            Intent(requireContext(), AllActiverUserActivity::class.java).apply {
                startActivity(this)
            }
        }


/*

        tv_see_all_teacher?.setOnClickListener {
            startActivity(Intent(requireContext(), AllTeachersActivity::class.java))
        }

        tv_see_all_ambassador?.setOnClickListener {
            startActivity(Intent(requireContext(), AllAmbassadorActivity::class.java))
        }
*/
/*

        tv_see_all_courses?.setOnClickListener {
            // startActivity(Intent(requireContext(), AllCoursesActivity::class.java))
            allCourseUrl = "https://course.starnotesocial.com/courses"

            val intent = Intent(requireContext(), CourseWebView::class.java)
            intent.putExtra("CourseID", allCourseUrl)
            startActivity(intent)

        }
*/

//        layout_btn_random_call.setOnClickListener {
//            addCourse()
//        }

        layout_btn_random_call?.setOnClickListener {

//            Intent(requireContext(), RandomCallOngoingActivity::class.java).apply {
//                startActivity(this)
//            }
            Intent(requireContext(), AgoraRandomVoiceCallActivity::class.java).apply {
                startActivity(this)
            }
            // addTestData()

//            Intent(requireContext(), KotlinBaseActivity::class.java).apply {
//                startActivity(this)
//            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Toast.makeText(requireContext(), "onDestroyView()", Toast.LENGTH_SHORT).show()
        if (listenerActiveList != null) {
            activeUserRef.removeEventListener(listenerActiveList!!)
        }
    }

/*

    private fun getTeamMembers() {


        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Users")
                .child("IDBT0XEmKL1dS9bdg0ZSD5SQfPTIr2")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                firstName = dataSnapshot.child("firstName").getValue(String::class.java)



                Log.d("DKSKKS", "onDataChange: $firstName ")
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }
*/

    private fun getActiveUserLists() {

        animatedLoading.showAnimatedLoading()
        val refLimit = activeUserRef.orderByKey().limitToFirst(8)
        val refCount = activeUserRef.orderByKey()

        val users = ArrayList<ActiveUser>()

        listenerActiveList = refCount.addValueEventListener(object : ValueEventListener {

            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val activeUserCount = dataSnapshot.childrenCount
                    txt_view_online_count_user?.text = "($activeUserCount)"
                    users.clear()
                    for (usersData in dataSnapshot.children) {

                        if (users.size == 8) {
                            break
                        }
                        val user = usersData.getValue(ActiveUser::class.java)

                        if (user?.firstName.isNullOrEmpty()) {
                            continue
                        }
                        users.add(user!!)
                    }
                    initActiveUser(users)
                    animatedLoading.hideAnimatedLoading()
                } else {
                    animatedLoading.hideAnimatedLoading()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
                TODO("Not yet implemented")
            }


        })

//        refCount.addValueEventListener(object: ValueEventListener {
//
//            @SuppressLint("SetTextI18n")
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val activeUserCount = dataSnapshot.childrenCount
//                    txt_view_title_with_online_count?.text = "Online ($activeUserCount)"
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })

    }


/*
    private fun getTeachersLists() {

        val ref =
            FirebaseDatabase.getInstance().getReference("Teachers").limitToLast(6)
        val teachers = ArrayList<Teacher>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    teachers.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(Teacher::class.java)
                        teachers.add(user!!)
                    }
                    initTeachersView(teachers)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun getAmbassadorsLists() {

        val ref =
            FirebaseDatabase.getInstance().getReference("Ambassadors").orderByKey().limitToLast(6)
        val ambassadors = ArrayList<Ambassador>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    ambassadors.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(Ambassador::class.java)
                        ambassadors.add(user!!)
                    }
                    iniAmbassadorView(ambassadors)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


    private fun getCampusAmbassadorsLists() {

        val ref =
            FirebaseDatabase.getInstance().getReference("CampusAmbassadors").orderByKey()
                .limitToLast(6)
        val ambassadors = ArrayList<CampusAmbassadorModel>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    ambassadors.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(CampusAmbassadorModel::class.java)
                        ambassadors.add(user!!)
                    }
                    initCampusAmbassadorView(ambassadors)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
*/


/*    private fun setAdapter(courseList: List<Data>) {
        adapter = CourseAdapterJava(requireContext(), courseList)
        rv_course_list.setAdapter(adapter)
    }

    private fun getAllCourses() {

        // Retrofit API Call..
        val call = RetrofitClientForCourse
            .getInstance()
            .serverApi
            .allCourses
        call.enqueue(object : Callback<GetCourses> {
            override fun onResponse(call: Call<GetCourses>, response: Response<GetCourses>) {
                Log.d("FDDK", "onResponse: " + response.body()!!.dataList[0].courseName)
                setAdapter(response.body()!!.dataList)
                adapter!!.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<GetCourses>, t: Throwable) {}
        })


*//*        serverApi.getAllCourses().enqueue(new Callback<GetCourses>() {
            @Override
            public void onResponse(Call<GetCourses> call, Response<GetCourses> response) {
                try {

                    if (response!=null){

                        response.body().getDataList().get(0).getCourseName();
                        setAdapter(response.body().getDataList());
                        adapter.notifyDataSetChanged();

                        Log.d("TAssG", "onResponse: "+response.body().getDataList().get(0).getCourseName().toString());
                        recyclerView.setAdapter(adapter);

                    }
                }catch (Exception e){

                    Log.d("TAssG", "Exception: "+e.getMessage().toString());

                }
            }

            @Override
            public void onFailure(Call<GetCourses> call, Throwable t) {
                Log.d("TAssG", "Failed: ");

            }
        });*//*
    }*/


/*
    private fun getCoursesLists() {

        val ref = database.getReference("Courses").orderByKey().limitToLast(5)
        val courses = ArrayList<Course>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    courses.clear()
                    for (courseData in dataSnapshot.children) {
                        val course = courseData.getValue(Course::class.java)
                        courses.add(course!!)
                    }

                    initCourseView(courses)
                } else {
                    top_view_course_title.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
*/


    private fun initActiveUser(users: List<ActiveUser>) {
        if (users.isNotEmpty()) {
            if (context != null) {
                // Toast.makeText(requireContext(), "${users.size}", Toast.LENGTH_SHORT).show()
                val activeLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val activeViewPool = RecyclerView.RecycledViewPool()

                activeLayoutManager.initialPrefetchItemCount = 4
                rv_active_online_user?.apply {
                    layoutManager = activeLayoutManager
                    setHasFixedSize(true)
                    adapter = UserOnlineViewAdapter(requireContext(), users)
                    setRecycledViewPool(activeViewPool)
                }
            }
        }


    }

/*
    private fun initTeachersView(users: List<Teacher>) {
        if (users.isNotEmpty()) {
            if (context != null) {
                val teacherLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val teacherViewPool = RecyclerView.RecycledViewPool()

                teacherLayoutManager.initialPrefetchItemCount = 2
                rv_teachers_list?.apply {
                    layoutManager = teacherLayoutManager
                    setHasFixedSize(true)
                    adapter = TeacherViewAdapter(requireContext(), users)
                    setRecycledViewPool(teacherViewPool)
                }


            }
        }

    }


    private fun iniAmbassadorView(users: List<Ambassador>) {
        if (users.isNotEmpty()) {
            if (context != null) {
                val ambassadorLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val ambassadorViewPool = RecyclerView.RecycledViewPool()

                ambassadorLayoutManager.initialPrefetchItemCount = 2
                rv_ambassador_list?.apply {
                    layoutManager = ambassadorLayoutManager
                    setHasFixedSize(true)
                    adapter = AmbassadorViewAdapter(requireContext(), users)
                    setRecycledViewPool(ambassadorViewPool)
                }
            }
        }
    }


    private fun initCampusAmbassadorView(users: List<CampusAmbassadorModel>) {
        if (users.isNotEmpty()) {

            if (context != null) {
                val campusambassadorLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val ambassadorViewPool = RecyclerView.RecycledViewPool()

                campusambassadorLayoutManager.initialPrefetchItemCount = 2
                rv_campus_ambassador_list?.apply {
                    layoutManager = campusambassadorLayoutManager
                    setHasFixedSize(true)
                    adapter = CampussCampusAmbassadorModelViewAdapter(requireContext(), users)
                    setRecycledViewPool(ambassadorViewPool)


                }
            }
        }


    }
*/


/*    private fun initCourseView(courses: List<Course>) {
        if (courses.isNotEmpty()) {
            if (context != null) {
                val courseLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val courseViewPool = RecyclerView.RecycledViewPool()

                courseLayoutManager.initialPrefetchItemCount = 2
                rv_course_list?.apply {
                    layoutManager = courseLayoutManager
                    setHasFixedSize(true)
                    adapter = CourseViewAdapter(requireContext(), courses)
                    setRecycledViewPool(courseViewPool)
                }
            }
        }


    }*/

    /**
     * Group List
     */

//    private fun getGroupList() {
//
//        val uID = userPreferences.getString("uID", null)
//        database.getReference("GroupConversation").orderByChild("lastTimestamps").limitToLast(10).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val listOfConversation = ArrayList<GroupConversation>()
//                for (ds in dataSnapshot.children) {
//                    if (!ds.child("Members").child(uID!!).exists()) {
//                        val groupChat = ds.getValue(GroupConversation::class.java)!!
//                        listOfConversation.add(groupChat)
//                    }
//                }
//
//                listOfConversation.reverse()
//
//                if (listOfConversation.size == 0) {
//                    group_layout_top_r_call.visibility = View.GONE
//                } else {
//                    group_layout_top_r_call.visibility = View.VISIBLE
//                }
//
//                // Recycler Adapter Changed..
//                groupChatListAdapter.removeAllItem()
//                groupChatListAdapter.addAllConversation(listOfConversation)
//                groupChatListAdapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }


    fun addTeacherData() {
//        val ambassador = Ambassador(
//            "hHWe6ILjVBVkkaGT2Qs8kR7SeY82",
//            "Jubida Islam",
//            "Opi",
//            "https://ftp.starnotesocial.com/upload/profile_images/16009177525541600917752351.jpg",
//            "Level 1",
//            0
//        )
//        val ref = database.getReference("Ambassadors").child(ambassador._id!!)
//        ref.setValue(ambassador)


        val teacher = Teacher(
            "99IKP56uNVXGMgVTRlfZjiRdeDj1",
            "HM Mahmudul",
            "Hasan",
            "https://ftp.starnotesocial.com/upload/profile_images/16001810835411600181083119.jpg",
            5.0,
            1,
            0
        )

        val ref = database.getReference("Teachers").child(teacher._id!!)
        ref.setValue(teacher)

    }


    fun addTestData() {

        val teachers = ArrayList<Teacher>()
        teachers.add(
            Teacher(
                "zHd4CJrXBqUrXR3QrHJXHU0H4112",
                "Sojol",
                "Islam",
                "https://lh3.googleusercontent.com/a-/AOh14GhcFSTygfNOelpagWEscDS1nvioGzdyZ-2qh5hnrA=s96-c",
                5.0,
                1
            )
        )
        teachers.add(
            Teacher(
                "jGsl8zYw0rfM3dyYqIE9IZNFcB52",
                "Abu Raihan",
                "Rony",
                "https://lh3.googleusercontent.com/a-/AOh14Ggs2ABE8llts6rWKzt80jM01HIRXZ9OjnWI_q6L=s96-c",
                5.0,
                1
            )
        )
        teachers.add(Teacher("aJg4jTRJ51bJsJgxd84M9SQEW7l1", "Ariful", "Islam", "none", 4.0, 1))
        teachers.add(
            Teacher(
                "ZfWkyxEOKrNdGa8lvH76sAVhQuX2",
                "Sajahan Ali",
                "Sardar",
                "https://graph.facebook.com/2276586095968680/picture",
                5.0,
                1
            )
        )
        teachers.add(
            Teacher(
                "UjtWoVM9tNPmY9xKSBLIH9jbPU33",
                "Yeakub",
                "Ali",
                "https://i.ibb.co/WtT0Tgh/3-jpeg.jpg",
                5.0,
                1
            )
        )
        teachers.add(
            Teacher(
                "LIsvRtL0OmZCmEmxNYt7IT6nGDj2",
                "Softlab IT",
                "Developer",
                "https://lh4.googleusercontent.com/-iIs-7aIC1BM/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmWhkSJJAQWr7hX-gvVNxWFUppL5w/s96-c/photo.jpg",
                3.0,
                1
            )
        )
        teachers.add(
            Teacher(
                "918sL022xAPSfZ1apfHUvKgtSfM2",
                "Mamunur",
                "Rasid",
                "https://lh6.googleusercontent.com/-98_9kqPncEM/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmweVgMLCq5Jx-5L90LdjDGVGzsRA/s96-c/photo.jpg",
                4.0,
                1
            )
        )


        val ambassadors = ArrayList<Ambassador>()
        ambassadors.add(
            Ambassador(
                "zHd4CJrXBqUrXR3QrHJXHU0H4112",
                "Sojol",
                "Islam",
                "https://lh3.googleusercontent.com/a-/AOh14GhcFSTygfNOelpagWEscDS1nvioGzdyZ-2qh5hnrA=s96-c",
                "Level 1",
                1
            )
        )
        ambassadors.add(
            Ambassador(
                "jGsl8zYw0rfM3dyYqIE9IZNFcB52",
                "Abu Raihan",
                "Rony",
                "https://lh3.googleusercontent.com/a-/AOh14Ggs2ABE8llts6rWKzt80jM01HIRXZ9OjnWI_q6L=s96-c",
                "Level 2",
                1
            )
        )
        ambassadors.add(
            Ambassador(
                "aJg4jTRJ51bJsJgxd84M9SQEW7l1",
                "Ariful",
                "Islam",
                "none",
                "Level 3",
                1
            )
        )
        ambassadors.add(
            Ambassador(
                "ZfWkyxEOKrNdGa8lvH76sAVhQuX2",
                "Sajahan Ali",
                "Sardar",
                "https://graph.facebook.com/2276586095968680/picture",
                "Level 1",
                1
            )
        )
        ambassadors.add(
            Ambassador(
                "UjtWoVM9tNPmY9xKSBLIH9jbPU33",
                "Yeakub",
                "Ali",
                "https://i.ibb.co/WtT0Tgh/3-jpeg.jpg",
                "Level 2",
                1
            )
        )
        ambassadors.add(
            Ambassador(
                "LIsvRtL0OmZCmEmxNYt7IT6nGDj2",
                "Softlab IT",
                "Developer",
                "https://lh4.googleusercontent.com/-iIs-7aIC1BM/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmWhkSJJAQWr7hX-gvVNxWFUppL5w/s96-c/photo.jpg",
                "Level 2",
                1
            )
        )
        ambassadors.add(
            Ambassador(
                "918sL022xAPSfZ1apfHUvKgtSfM2",
                "Mamunur",
                "Rasid",
                "https://lh6.googleusercontent.com/-98_9kqPncEM/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmweVgMLCq5Jx-5L90LdjDGVGzsRA/s96-c/photo.jpg",
                "Level 1",
                1
            )
        )

//        teachers.forEach {
//            val myCourseRef = database.getReference("Teachers").child(it._id!!)
//            myCourseRef.setValue(it)
//        }

//        ambassadors.forEach {
//            val myCourseRef = database.getReference("Ambassadors").child(it._id!!)
//            myCourseRef.setValue(it)
//        }

    }


}
package com.example.studentski_raspored.helpers

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.studentski_raspored.adapters.EntryRecyclerAdapter
import com.example.studentski_raspored.fragments.DayFragment
import com.example.studentski_raspored.models.Entry
import com.example.studentski_raspored.models.Subject
import com.example.studentski_raspored.models.Task
import com.example.studentski_raspored.models.User
import com.example.studentski_raspored.ui.MainActivity
import com.example.studentski_raspored.ui.SubjectActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

object Firebase {
    private val dbInstance =
        FirebaseDatabase.getInstance("https://scheduler-77a6b-default-rtdb.europe-west1.firebasedatabase.app/")
    private val usersDbReference = dbInstance.getReference("Users")
    private val tasksDbReference = dbInstance.getReference("Tasks")
    private val subjectsDbReference = dbInstance.getReference("Subjects")
    private val entriesDbReference = dbInstance.getReference("Entry")
    private var childEventListenerTasks: ChildEventListener? = null
    private var childEventListenerSubjects: ChildEventListener? = null
    private var childEventListenerEntries: ChildEventListener? = null

    fun loginUser(username: String, password: String, context: Context) {
        usersDbReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children
                var isFound = false

                users.forEach {
                    val user = it.getValue<User>()
                    if (user != null) {
                        if (user.username == username && user.password == password) {
                            val intent = Intent(context, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                            isFound = true
                            Log.e("Authenticate", "Login successful")
                            user.subjects?.let { it1 -> DataHolder.setUserSubjects(it1) }
                            user.id?.let { it1 -> DataHolder.setUserId(it1) }
                        } else if (user.username == username && user.password != password) {
                            isFound = true
                            Toast.makeText(context, "Kriva lozinka", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                if (!isFound) {
                    registerNewUser(username, password, context)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
    }

    fun registerNewUser(username: String, password: String, context: Context) {
        val id = usersDbReference.push().key as String
        val user = User(id, username, password)
        usersDbReference.child(id).setValue(user).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                Log.e("Authenticate", "Register successful")
                DataHolder.setUserSubjects("")
                user.id?.let { it1 -> DataHolder.setUserId(it1) }
            } else {
                Toast.makeText(context, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun pushTest(
        id: String,
        name: String,
        date: String,
        day: String,
        time: String,
        location: String,
        context: Context
    ) {
        val dbId = entriesDbReference.push().key as String
        val entry = Entry(id, name, date, day, time, location)

        entriesDbReference.child(dbId).setValue(entry).addOnCompleteListener {
            if (it.isSuccessful)
                Log.e("Dodan entry", entry.toString())
            else {
                Toast.makeText(context, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun getTasks() {
        childEventListenerTasks = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val tasks = mutableListOf<Task>()

                val taskData = snapshot.value as HashMap<*, *>
                val task = Task(
                    taskData["title"].toString(),
                    taskData["description"].toString(),
                    taskData["url"].toString()
                )
                tasks.add(task)
                MainActivity.taskAdapter.dataAdded(tasks)
            }

            override fun onCancelled(error: DatabaseError) {}
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
        }

        val query: Query = tasksDbReference.orderByChild("title")
        query.addChildEventListener(childEventListenerTasks as ChildEventListener)
    }

    fun getSubjects() {
        childEventListenerSubjects = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val subjects = mutableListOf<Subject>()

                val subjectData = snapshot.value as HashMap<*, *>
                val subject = Subject(
                    subjectData["name"].toString(),
                    subjectData["year"].toString(),
                    subjectData["semester"].toString(),
                    subjectData["id"].toString()
                )
                subjects.add(subject)
                SubjectActivity.subjectAdapter.dataAdded(subjects)
            }

            override fun onCancelled(error: DatabaseError) {}
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
        }

        val query: Query = subjectsDbReference.orderByChild("name")
        query.addChildEventListener(childEventListenerSubjects as ChildEventListener)
    }

    fun changeUserSubjects(userId: String, subjects: String, context: Activity) {
        usersDbReference.child(userId).child("subjects").setValue(subjects).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Change successful!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getEntries(day: String, adapter: EntryRecyclerAdapter, subjects: List<String>) {
        childEventListenerEntries = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val entries = mutableListOf<Entry>()

                val entryData = snapshot.value as HashMap<*, *>
                val entry = Entry(
                    entryData["id"].toString(),
                    entryData["name"].toString(),
                    entryData["date"].toString(),
                    entryData["day"].toString(),
                    entryData["time"].toString(),
                    entryData["location"].toString(),
                )

                /*Log.e("subj", "Contains: " + subjects.contains(entry.id) + " id: " + entry.id)*/
                if (entry.date.equals(day) && subjects.contains(entry.id))
                    entries.add(entry)
                adapter.dataAdded(entries)
            }

            override fun onCancelled(error: DatabaseError) {}
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
        }

        val query: Query = entriesDbReference.orderByChild("name")
        query.addChildEventListener(childEventListenerEntries as ChildEventListener)
    }
}
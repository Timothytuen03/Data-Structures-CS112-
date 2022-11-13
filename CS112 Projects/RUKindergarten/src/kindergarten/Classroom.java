package kindergarten;

import java.util.prefs.NodeChangeEvent;

/**
 * This class represents a Classroom, with:
 * - an SNode instance variable for students in line,
 * - an SNode instance variable for musical chairs, pointing to the last student in the list,
 * - a boolean array for seating availability (eg. can a student sit in a given seat), and
 * - a Student array parallel to seatingAvailability to show students filed into seats 
 * --- (more formally, seatingAvailability[i][j] also refers to the same seat in studentsSitting[i][j])
 * 
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
    private SNode studentsInLine;             // when students are in line: references the FIRST student in the LL
    private SNode musicalChairs;              // when students are in musical chairs: references the LAST student in the CLL
    private boolean[][] seatingAvailability;  // represents the classroom seats that are available to students
    private Student[][] studentsSitting;      // when students are sitting in the classroom: contains the students

    /**
     * Constructor for classrooms. Do not edit.
     * @param l passes in students in line
     * @param m passes in musical chairs
     * @param a passes in availability
     * @param s passes in students sitting
     */
    public Classroom ( SNode l, SNode m, boolean[][] a, Student[][] s ) {
		studentsInLine      = l;
        musicalChairs       = m;
		seatingAvailability = a;
        studentsSitting     = s;
	}
    /**
     * Default constructor starts an empty classroom. Do not edit.
     */
    public Classroom() {
        this(null, null, null, null);
    }

    /**
     * This method simulates students coming into the classroom and standing in line.
     * 
     * Reads students from input file and inserts these students in alphabetical 
     * order to studentsInLine singly linked list.
     * 
     * Input file has:
     * 1) one line containing an integer representing the number of students in the file, say x
     * 2) x lines containing one student per line. Each line has the following student 
     * information separated by spaces: FirstName LastName Height
     * 
     * @param filename the student information input file
     */
    public void makeClassroom (String filename) {

        // WRITE YOUR CODE HERE

        StdIn.setFile(filename);
        int numStu = StdIn.readInt();

        Student[] students = new Student[numStu];
 
        //Fill in array of students
        for(int i = 0; i < numStu; i++)
        {
            String first = StdIn.readString();
            String last = StdIn.readString();
            int height = StdIn.readInt();

           Student s = new Student(first, last, height);
           //s.setFirstName(StdIn.readString());
           //s.setLastName(StdIn.readString());
           //s.setHeight(StdIn.readInt());
           students[i] = s;
        }

        //Insertion Sort
        int itemsSorted;
        for(itemsSorted = 1; itemsSorted < students.length; itemsSorted++)
        {
            Student temp = students[itemsSorted];
            int loc = itemsSorted-1;
            while(loc >= 0 && students[loc].compareNameTo(temp) > 0)
            {
                students[loc+1] = students[loc];
                loc -= 1;
            }
            students[loc +1] = temp;
        }

        /*for(int i = 0; i < students.length; i++)
        {
            System.out.println(students[i].getFullName());
        }*/
     
        /*SNode p = new SNode();
        p.setStudent(students[0]);
        studentsInLine = p;
        System.out.println(studentsInLine.getStudent().getFullName());*/

        //Populate StudentsInLine Linked List
        studentsInLine = new SNode();
        studentsInLine.setStudent(students[0]);
        SNode ptr = studentsInLine;
        //SNode n = new SNode();
        //newStudent.setNext(n);
        //newStudent = newStudent.getNext();

        for(int i = 1; i < numStu; i++)
        {
            SNode studentsLine = new SNode();
            studentsLine.setStudent(students[i]);
            studentsInLine.setNext(studentsLine);

            studentsInLine = studentsInLine.getNext();
        }

        studentsInLine = ptr;

        //System.out.println(studentsSitting);
    }

    /**
     * 
     * This method creates and initializes the seatingAvailability (2D array) of 
     * available seats inside the classroom. Imagine that unavailable seats are broken and cannot be used.
     * 
     * Reads seating chart input file with the format:
     * An integer representing the number of rows in the classroom, say r
     * An integer representing the number of columns in the classroom, say c
     * Number of r lines, each containing c true or false values (true denotes an available seat)
     *  
     * This method also creates the studentsSitting array with the same number of
     * rows and columns as the seatingAvailability array
     * 
     * This method does not seat students on the seats.
     * 
     * @param seatingChart the seating chart input file
     */
    public void setupSeats(String seatingChart) {

	// WRITE YOUR CODE HERE
    StdIn.setFile(seatingChart);
    int rows = StdIn.readInt();
    int cols = StdIn.readInt();

    seatingAvailability = new boolean[rows][cols];
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            seatingAvailability[i][j] = StdIn.readBoolean();
        }
    }

    Student[][] sitting = new Student[rows][cols];
    studentsSitting = sitting;
    //System.out.println(studentsSitting);
    //System.out.println(studentsInLine.getStudent());

    }

    /**
     * 
     * This method simulates students taking their seats in the classroom.
     * 
     * 1. seats any remaining students from the musicalChairs starting from the front of the list
     * 2. starting from the front of the studentsInLine singly linked list
     * 3. removes one student at a time from the list and inserts them into studentsSitting according to
     *    seatingAvailability
     * 
     * studentsInLine will then be empty
     */
    public void seatStudents () {

	// WRITE YOUR CODE HERE

    for(int row = 0; row < seatingAvailability.length; row++)
    {
        for(int col = 0; col < seatingAvailability[0].length; col++)
        {
            if(seatingAvailability[row][col] != false)
            {
                if(studentsInLine != null)
                { 
                    if(musicalChairs != null)
                    {
                        studentsSitting[row][col] = musicalChairs.getStudent();
                        //musicalChairs = musicalChairs.getNext();
                        musicalChairs = null;
                    }   
                    else
                    {
                        studentsSitting[row][col] = studentsInLine.getStudent();
                        studentsInLine = studentsInLine.getNext();
                    }
                }
                
                //else if(studentsInLine.getNext() == null && studentsInLine != null)
                //{
                    //studentsSitting[row][col] = studentsInLine.getStudent();
                    //studentsInLine = null;
                    //break;
                //}
            }
        }
    }   
    //studentsInLine = null;
    

    }

    /**
     * Traverses studentsSitting row-wise (starting at row 0) removing a seated
     * student and adding that student to the end of the musicalChairs list.
     * 
     * row-wise: starts at index [0][0] traverses the entire first row and then moves
     * into second row.
     */
    public void insertMusicalChairs () {
        
        // WRITE YOUR CODE HERE)

        musicalChairs = new SNode();
        SNode oldLast = musicalChairs;
        musicalChairs.setNext(musicalChairs);

        for(int row = 0; row < studentsSitting.length; row++)
        {
            for(int col = 0; col < studentsSitting[0].length; col++)
            {
                //if(musicalChairs.getNext() != null)
                //{
                if (studentsSitting[row][col] != null)
                {
                    //if(row == 0 && col == 0)
                    //{
                        //musicalChairs.setStudent(studentsSitting[row][col]);
                    //}
                    //else
                    //{
                        SNode stu = new SNode();
                        stu.setStudent(studentsSitting[row][col]);
                        studentsSitting[row][col] = null;
                        musicalChairs.setNext(stu);
                        if(row != studentsSitting.length && col != studentsSitting[0].length)
                        {
                            musicalChairs = musicalChairs.getNext();
                        }
                    //}
                }
            }
        }
        musicalChairs.setNext(oldLast.getNext());


        /*studentsInLine = new SNode();
        studentsInLine.setStudent(students[0]);
        SNode ptr = studentsInLine;
        //SNode n = new SNode();
        //newStudent.setNext(n);
        //newStudent = newStudent.getNext();

        for(int i = 1; i < numStu; i++)
        {
            SNode studentsLine = new SNode();
            studentsLine.setStudent(students[i]);
            studentsInLine.setNext(studentsLine);

            studentsInLine = studentsInLine.getNext();
        }

        studentsInLine = ptr;*/
     }

    /**
     * 
     * This method repeatedly removes students from the musicalChairs until there is only one
     * student (the winner).
     * 
     * Choose a student to be elimnated from the musicalChairs using StdRandom.uniform(int b),
     * where b is the number of students in the musicalChairs. 0 is the first student in the 
     * list, b-1 is the last.
     * 
     * Removes eliminated student from the list and inserts students back in studentsInLine 
     * in ascending height order (shortest to tallest).
     * 
     * The last line of this method calls the seatStudents() method so that students can be seated.
     */
    public void playMusicalChairs() {

        // WRITE YOUR CODE HERE
        
        int counter = 0;
        SNode ptr = musicalChairs.getNext();

        while(ptr != musicalChairs)
        {
            counter++;
            ptr = ptr.getNext();
        }

        int numRemoved = 0;
        int studentAmount = counter+1;

        while(numRemoved < counter)
        {
            SNode prevMusic = musicalChairs;
            SNode ptrMusic = musicalChairs.getNext();

            //System.out.println("Number of Students Removed: " + numRemoved + "Student Amount: " + studentAmount);

            int random = StdRandom.uniform(studentAmount);
            for(int i = 0; i < random; i++)
            {
                prevMusic = ptrMusic;
                //System.out.println(ptrMusic.getStudent().getFullName());
                ptrMusic = ptrMusic.getNext();
            }
            //Removes the target
            //System.out.println(ptrMusic.getStudent().getFullName());

            prevMusic.setNext(ptrMusic.getNext());
            ptrMusic.setNext(null);
            SNode s = new SNode(ptrMusic.getStudent(), null);

            
            if(ptrMusic == musicalChairs)
            {
                musicalChairs = prevMusic;
            }

            if(studentsInLine == null)
            {
                studentsInLine = s;
            }
            else
            {
                SNode ptrStu = studentsInLine;
                SNode prevStu = null;
                //prevMusic = null;
                while(ptrStu != null)
                {
                    if(s.getStudent().getHeight() <= ptrStu.getStudent().getHeight())
                    {
                        if(prevStu == null)
                        {
                            s.setNext(ptrStu);
                            prevStu = s;
                            //prevStu.setNext(ptrStu);
                            studentsInLine = prevStu;
                        }
                        else{
                            s.setNext(ptr);
                            prevStu.setNext(s);
                            prevStu = prevStu.getNext();
                        }
                        prevStu.setNext(ptrStu);
                        break;
                    }
                    if(ptrStu.getNext() == null)
                    {
                        ptrStu.setNext(s);
                        break;
                    }

                    if(prevStu == null)
                    {
                        prevStu = ptrStu;
                    }
                    else{
                        prevStu = prevStu.getNext();
                    }
                    ptrStu = ptrStu.getNext();
                }
                
 
            } 
            SNode test = studentsInLine;
            while (test!= null)
            {
            //System.out.println("students in Line: " + test.getStudent().getFullName());
            test =test.getNext();
            }

            numRemoved++;
            //counter--;
            studentAmount--;
        }
        seatStudents();
    } 

    /**
     * Insert a student to wherever the students are at (ie. whatever activity is not empty)
     * Note: adds to the end of either linked list or the next available empty seat
     * @param firstName the first name
     * @param lastName the last name
     * @param height the height of the student
     */
    public void addLateStudent ( String firstName, String lastName, int height ) {
        
        // WRITE YOUR CODE HERE
        SNode lateStudent = new SNode();
        Student ls = new Student(firstName, lastName, height);
        lateStudent.setStudent(ls);

        SNode lineFront = studentsInLine;
        SNode oldLast = musicalChairs;
        Boolean sit = false;

        if(studentsInLine != null)
        {
            while(studentsInLine.getNext() != null)
            {
                studentsInLine = studentsInLine.getNext();
            }
            studentsInLine.setNext(lateStudent);
            studentsInLine = lineFront;
        }
        else if(musicalChairs != null)
        {
            lateStudent.setNext(oldLast.getNext());
            oldLast.setNext(lateStudent);
            musicalChairs = lateStudent;
        }
        else if(studentsSitting != null)
        {
            for(int i = 0; i < studentsSitting.length; i++)
            {
                for(int j = 0; j < studentsSitting[0].length; j++)
                {
                    if(studentsSitting[i][j] == null && seatingAvailability[i][j] != false)
                    {
                        //System.out.println(i + ", " + j);
                        if(sit == false)
                        {   
                            //System.out.println("change");
                            studentsSitting[i][j] = ls;
                            sit = true;
                            break;
                        }
                    }
                }
            }
            //studentsSitting[studentsSitting.length-1][studentsSitting[0].length-1] = ls;
        }
    }

    /**
     * A student decides to leave early
     * This method deletes an early-leaving student from wherever the students 
     * are at (ie. whatever activity is not empty)
     * 
     * Assume the student's name is unique
     * 
     * @param firstName the student's first name
     * @param lastName the student's last name
     */
    public void deleteLeavingStudent ( String firstName, String lastName ) {

        // WRITE YOUR CODE HERE
        String name = firstName + " " + lastName;
      

        if(studentsInLine != null)
        {
            SNode ptr = studentsInLine.getNext();
            SNode prev = studentsInLine;
            if(studentsInLine.getStudent().getFullName().compareTo(name) ==0)
            {
                SNode s = new SNode();
                s = studentsInLine.getNext();
                studentsInLine = s;
            }
            else{
                while(ptr!= null && ptr.getStudent().getFullName().compareTo(name) != 0)
                {
                    ptr = ptr.getNext();
                    prev = prev.getNext();
                }
                if(ptr!=null)
                {
                prev.setNext(ptr.getNext());
                }
            }
        }
        else if(musicalChairs != null)
        {
            SNode ptr = musicalChairs.getNext();
            SNode ptrPrev = musicalChairs;
            SNode oldLast = musicalChairs;
            SNode oldPrev = musicalChairs;
            int numStudents = 1;
            while(oldPrev.getNext() != musicalChairs)
            {
                numStudents++;
                oldPrev = oldPrev.getNext();
            }
            for(int i = 0; i < numStudents; i++)
            {
                if(ptr.getStudent().getFullName().compareTo(name) == 0 && ptr == oldLast)
                {
                    ptrPrev.setNext(ptr.getNext());
                    musicalChairs = ptrPrev;

                    //System.out.println("old last");
                    //musicalChairs = oldPrev;
                }
                else if(ptr.getStudent().getFullName().compareTo(name) == 0)
                {
                    ptrPrev.setNext(ptr.getNext());
                    //ptr.setNext(null);
                    //musicalChairs.setNext(musicalChairs.getNext().getNext());
                    musicalChairs = oldLast;
                }
                else
                {
                    ptr = ptr.getNext();
                    ptrPrev = ptrPrev.getNext();
                }
            }
        }
        else if(studentsSitting != null)
        {
            for(int i = 0; i < studentsSitting.length; i++)
            {
                for(int j = 0; j < studentsSitting[0].length; j++)
                {
                    if(studentsSitting[i][j] != null)
                    {
                        if(studentsSitting[i][j].getFullName().compareTo(name) == 0)
                        {
                            studentsSitting[i][j] = null;
                        }
                    }
                }
            }
        }
    }

    /**
     * Used by driver to display students in line
     * DO NOT edit.
     */
    public void printStudentsInLine () {

        //Print studentsInLine
        StdOut.println ( "Students in Line:" );
        if ( studentsInLine == null ) { StdOut.println("EMPTY"); }

        for ( SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext() ) {
            StdOut.print ( ptr.getStudent().print() );
            if ( ptr.getNext() != null ) { StdOut.print ( " -> " ); }
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Prints the seated students; can use this method to debug.
     * DO NOT edit.
     */
    public void printSeatedStudents () {

        StdOut.println("Sitting Students:");

        if ( studentsSitting != null ) {
        
            for ( int i = 0; i < studentsSitting.length; i++ ) {
                for ( int j = 0; j < studentsSitting[i].length; j++ ) {

                    String stringToPrint = "";
                    if ( studentsSitting[i][j] == null ) {

                        if (seatingAvailability[i][j] == false) {stringToPrint = "X";}
                        else { stringToPrint = "EMPTY"; }

                    } else { stringToPrint = studentsSitting[i][j].print();}

                    StdOut.print ( stringToPrint );
                    
                    for ( int o = 0; o < (10 - stringToPrint.length()); o++ ) {
                        StdOut.print (" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    /**
     * Prints the musical chairs; can use this method to debug.
     * DO NOT edit.
     */
    public void printMusicalChairs () {
        StdOut.println ( "Students in Musical Chairs:" );

        if ( musicalChairs == null ) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for ( ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext() ) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if ( ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    /**
     * Prints the state of the classroom; can use this method to debug.
     * DO NOT edit.
     */
    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    /**
     * Used to get and set objects.
     * DO NOT edit.
     */

    public SNode getStudentsInLine() { return studentsInLine; }
    public void setStudentsInLine(SNode l) { studentsInLine = l; }

    public SNode getMusicalChairs() { return musicalChairs; }
    public void setMusicalChairs(SNode m) { musicalChairs = m; }

    public boolean[][] getSeatingAvailability() { return seatingAvailability; }
    public void setSeatingAvailability(boolean[][] a) { seatingAvailability = a; }

    public Student[][] getStudentsSitting() { return studentsSitting; }
    public void setStudentsSitting(Student[][] s) { studentsSitting = s; }

}

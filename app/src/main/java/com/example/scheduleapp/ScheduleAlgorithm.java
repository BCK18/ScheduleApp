package com.example.scheduleapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class ScheduleAlgorithm {

    /*// create employee list for testing.
    private static ArrayList<Employee> create_employee_list(){

        ArrayList<Employee> employee_list = new ArrayList<Employee>();

        Employee jason = new Employee(1, "Jason", true, false);
        employee_list.add(jason);

        Employee clear = new Employee(2, "Clear", true, true);
        employee_list.add(clear);

        Employee tarik = new Employee(3, "Tarik", false, false);
        employee_list.add(tarik);

        Employee brandon = new Employee(4, "Brandon", false, true);
        employee_list.add(brandon);

        Employee marco = new Employee(5, "Marco", true, true);
        employee_list.add(marco);

        Employee altaf = new Employee(6, "Altaf", false, false);
        employee_list.add(altaf);

        Employee bob = new Employee(7, "Bob", true, true);
        employee_list.add(bob);

        Employee dylan = new Employee(8, "Dylan", false, false);
        employee_list.add(dylan);

        Employee michael = new Employee(9, "Michael", true, false);
        employee_list.add(michael);

        Employee jordan = new Employee(10, "Jordan", false, true);
        employee_list.add(jordan);

        return employee_list;
    }
    */

    // print employee list.
    private static void print_employees(ArrayList<Employee> employee_list){

        System.out.println("\n");

        for (int i = 0; i < employee_list.size(); i++){

            System.out.println(employee_list.get(i).toString());
        }

        System.out.println("\n");

        return;
    }


    // create list of employees that need opening training.
    private static ArrayList<Employee> create_untrained_opening(ArrayList<Employee> employee_list){
        // this function could be combined with create_trained_opening() to return two lists.
        // this modification would involve returning a set of data.

        ArrayList<Employee> untrained_opening = new ArrayList<Employee>();

        for (int i = 0; i < employee_list.size(); i++){
            if (employee_list.get(i).getOpening() == false){
                untrained_opening.add(employee_list.get(i));
            }
        }

        return untrained_opening;
    }


    // create list of employees that need closing training.
    private static ArrayList<Employee> create_untrained_closing(ArrayList<Employee> employee_list){
        // this function could be combined with create_trained_closing() to return two lists.
        // this modification would involve returning a set of data.

        ArrayList<Employee> untrained_closing = new ArrayList<Employee>();

        for (int i = 0; i < employee_list.size(); i++){
            if (employee_list.get(i).getClosing() == false){
                untrained_closing.add(employee_list.get(i));
            }
        }

        return untrained_closing;
    }


    // create list of employees that are trained for opening.
    private static ArrayList<Employee> create_trained_opening(ArrayList<Employee> employee_list){
        // this function could be combined with create_untrained_opening() to return two lists.
        // this modification would involve returning a set of data.

        ArrayList<Employee> trained_opening = new ArrayList<Employee>();

        for (int i = 0; i < employee_list.size(); i++){
            if (employee_list.get(i).getOpening() == true){
                trained_opening.add(employee_list.get(i));
            }
        }

        return trained_opening;
    }


    // create list of employees that are trained for closing.
    private static ArrayList<Employee> create_trained_closing(ArrayList<Employee> employee_list){
        // this function could be combined with create_trained_closing() to return two lists.
        // this modification would involve returning a set of data.

        ArrayList<Employee> trained_closing = new ArrayList<Employee>();

        for (int i = 0; i < employee_list.size(); i++){
            if (employee_list.get(i).getClosing() == true){
                trained_closing.add(employee_list.get(i));
            }
        }

        return trained_closing;
    }


    // create new week.
    private static Week create_week(ArrayList<LocalDate> date_range){
        // NOTE: need to develop a better way for generating days (simplify the for loop to resuse if statements).
        // (is this even possible, without losing important information?)

        // NOTE: a function is needed for calculating a date range.
        Week new_week = new Week(date_range);

        // initialize week.
        for (int i = 0; i < 7; i++){

            if (i == 0){
                Day sunday = new Day(date_range.get(0), "Sunday", "Weekend");
                sunday.addShift(new Shift("Full-day", date_range.get(0)));
                new_week.addDay(sunday);
            }

            else if (i == 1){
                Day monday = new Day(date_range.get(1), "Monday", "Weekday");
                monday.addShift(new Shift("Opening", date_range.get(1)));
                monday.addShift(new Shift("Mid-day", date_range.get(1)));
                monday.addShift(new Shift("Closing", date_range.get(1)));
                new_week.addDay(monday);
            }

            else if (i == 2){

                Day tuesday = new Day(date_range.get(2), "Tuesday", "Weekday");
                tuesday.addShift(new Shift("Opening", date_range.get(2)));
                tuesday.addShift(new Shift("Mid-day", date_range.get(2)));
                tuesday.addShift(new Shift("Closing", date_range.get(2)));
                new_week.addDay(tuesday);
            }

            else if (i == 3){

                Day wednesday = new Day(date_range.get(3), "Wednesday", "Weekday");
                wednesday.addShift(new Shift("Opening", date_range.get(3)));
                wednesday.addShift(new Shift("Mid-day", date_range.get(3)));
                wednesday.addShift(new Shift("Closing", date_range.get(3)));
                new_week.addDay(wednesday);
            }

            else if (i == 4){

                Day thursday = new Day(date_range.get(4), "Thursday", "Weekday");
                thursday.addShift(new Shift("Opening", date_range.get(4)));
                thursday.addShift(new Shift("Mid-day", date_range.get(4)));
                thursday.addShift(new Shift("Closing", date_range.get(4)));
                new_week.addDay(thursday);
            }

            else if (i == 5){

                Day friday = new Day(date_range.get(5), "Friday", "Weekday");
                friday.addShift(new Shift("Opening", date_range.get(5)));
                friday.addShift(new Shift("Mid-day", date_range.get(5)));
                friday.addShift(new Shift("Closing", date_range.get(5)));
                new_week.addDay(friday);
            }

            else if (i == 6){
                Day saturday = new Day(date_range.get(6), "Saturday", "Weekend");
                saturday.addShift(new Shift("Full-day", date_range.get(6)));
                new_week.addDay(saturday);
            }
        }

        return new_week;
    }


    // check if the given employee has been scheduled to work on the given day.
    private static boolean shifts_check(Employee employee, Day day){

        boolean check = false;

        for (int i = 0; i < day.shifts.size(); i++){

            Shift current_shift = day.shifts.get(i);

            if (current_shift.supervisor != null){

                if (current_shift.supervisor.id == employee.id){

                    check = true;
                }
            }

            if (current_shift.second != null){

                if (current_shift.second.id == employee.id){

                    check = true;
                }
            }
        }

        return check;
    }


    // remove emloyee from the not_worked list.
    private static void worked(Employee employee, ArrayList<Employee> not_worked){

        for (int i = 0; i < not_worked.size(); i++){

            if (employee.id == not_worked.get(i).id){

                not_worked.remove(i);
            }
        }

        return;
    }


    // first pass: add employees that need opening training to the schedule.
    private static void opening_training_check(Week test_week, ArrayList<Employee> untrained_opening, ArrayList<Employee> completed_opening, ArrayList<Employee> not_worked){

        for (int i = 0; i < test_week.days.size(); i++){

            Day current_day = test_week.days.get(i);

            for (int j = 0; j < untrained_opening.size(); j++){

                pass1_check:
                if (current_day.shifts.get(0).second == null){

                    Employee current_employee = untrained_opening.get(j);
                    boolean found = false;

                    for (int k = 0; k < completed_opening.size(); k++){

                        if (current_employee.id == completed_opening.get(k).id){

                            found = true;
                        }
                    }

                    if (found == false){

                        current_day.shifts.get(0).second = current_employee;
                        current_day.addWorker(current_employee);
                        completed_opening.add(current_employee);
                        worked(current_employee, not_worked);
                        break pass1_check;
                    }
                }
            }
        }

        return;
    }


    // second pass: add employees that need closing training to the schedule.
    private static void closing_training_check(Week test_week, ArrayList<Employee> untrained_closing, ArrayList<Employee> completed_closing, ArrayList<Employee> not_worked){

        for (int i = 0; i < test_week.days.size(); i++){

            Day current_day = test_week.days.get(i);

            for (int j = 0; j < untrained_closing.size(); j++){

                Employee current_employee = untrained_closing.get(j);
                boolean found = false;

                if (current_day.day_type == "Weekend"){

                    pass2_check:
                    if (current_day.shifts.get(0).second == null){

                        for (int k = 0; k < completed_closing.size(); k++){

                            if(current_employee.id == completed_closing.get(k).id){

                                found = true;
                            }
                        }

                        if (found == false){

                            current_day.shifts.get(0).second = current_employee;
                            current_day.addWorker(current_employee);
                            completed_closing.add(current_employee);
                            worked(current_employee, not_worked);
                            break pass2_check;
                        }
                    }
                }

                else {

                    pass2_check:
                    if (current_day.shifts.get(2).second == null){

                        for (int k = 0; k < completed_closing.size(); k++){

                            if(current_employee.id == completed_closing.get(k).id){

                                found = true;
                            }
                        }

                        if (found == false){

                            current_day.shifts.get(2).second = current_employee;
                            current_day.addWorker(current_employee);
                            completed_closing.add(current_employee);
                            worked(current_employee, not_worked);
                            break pass2_check;
                        }
                    }
                }
            }
        }

        return;
    }


    // third pass: add supervisors for opening shifts + weekend full-day shifts.
    public static void opening_supervisor_check(Week test_week, ArrayList<Employee> trained_opening, ArrayList<Employee> not_worked){

        for (int i = 0; i < test_week.days.size(); i++){

            Day current_day = test_week.days.get(i);

            if (current_day.day_type == "Weekend"){

                pass3_check:
                if (current_day.shifts.get(0).supervisor == null){

                    for (int j = 0; j < trained_opening.size(); j++){

                        Employee current_employee = trained_opening.get(j);

                        if (current_employee.closing == true){

                            current_day.shifts.get(0).supervisor = current_employee;
                            current_day.addWorker(current_employee);
                            worked(current_employee, not_worked);
                            break pass3_check;
                        }
                    }
                }
            }

            else {

                pass3_check:
                if (current_day.shifts.get(0).supervisor == null){

                    for (int j = 0; j < trained_opening.size(); j++){


                        Employee current_employee = trained_opening.get(j);

                        if (shifts_check(current_employee, current_day) == false){

                            current_day.shifts.get(0).supervisor = current_employee;
                            current_day.addWorker(current_employee);
                            worked(current_employee, not_worked);
                            break pass3_check;
                        }
                    }
                }
            }
        }

        return;
    }


    // fourth pass: add supervisors for weekday closing shifts (weekend full-day closing shifts were covered in third pass).
    private static void closing_supervisor_check(Week test_week, ArrayList<Employee> trained_closing, ArrayList<Employee> not_worked){

        for (int i = 0; i < test_week.days.size(); i++){

            Day current_day = test_week.days.get(i);

            if (current_day.day_type == "Weekday"){

                pass4_check:
                if (current_day.shifts.get(2).supervisor == null){

                    for (int j = 0; j < trained_closing.size(); j++){

                        Employee current_employee = trained_closing.get(j);

                        if (shifts_check(current_employee, current_day) == false){

                            current_day.shifts.get(2).supervisor = current_employee;
                            current_day.addWorker(current_employee);
                            worked(current_employee, not_worked);
                            break pass4_check;
                        }
                    }
                }
            }
        }

        return;
    }


    // fifth pass: add employees that have not yet been scheduled to empty shifts.
    public static void not_worked_check(Week test_week, ArrayList<Employee> not_worked){

        pass5_check:
        while (not_worked.size() > 0){

            Employee current_employee = not_worked.get(0);

            for (int i = 0; i < test_week.days.size(); i++){

                Day current_day = test_week.days.get(i);

                for (int j = 0; j < current_day.shifts.size(); j++){

                    if (current_day.shifts.get(j).second == null){

                        current_day.shifts.get(j).second = current_employee;
                        current_day.addWorker(current_employee);
                        worked(current_employee, not_worked);
                        break pass5_check;
                    }

                    if (current_day.shifts.get(j).supervisor == null){

                        current_day.shifts.get(j).supervisor = current_employee;
                        current_day.addWorker(current_employee);
                        worked(current_employee, not_worked);
                        break pass5_check;
                    }
                }
            }
        }

        return;
    }


    // sixth pass: fill in remaining empty shifts.
    private static void empty_shift_check(Week test_week, ArrayList<Employee> employee_list){

        Random random = new Random();

        for (int i = 0; i < test_week.days.size(); i++){

            Day current_day = test_week.days.get(i);

            for (int j = 0; j < current_day.shifts.size(); j++){

                Shift current_shift = current_day.shifts.get(j);

                int rand_int = random.nextInt(employee_list.size() - 1);
                Employee random_worker = employee_list.get(rand_int);

                if (current_shift.second == null){

                    while (current_day.workerCheck(random_worker) == true){

                        rand_int = random.nextInt(employee_list.size() - 1);
                        random_worker = employee_list.get(rand_int);
                    }

                    current_shift.second = random_worker;
                    current_day.addWorker(random_worker);
                }

                if (current_shift.supervisor == null){

                    while (current_day.workerCheck(random_worker) == true){

                        rand_int = random.nextInt(employee_list.size() - 1);
                        random_worker = employee_list.get(rand_int);
                    }

                    current_shift.supervisor = random_worker;
                    current_day.addWorker(random_worker);
                }
            }
        }

        return;
    }


    // update training status for employees that have completed training.
    private static void update_training(ArrayList<Employee> completed_opening, ArrayList<Employee> completed_closing){

        for (int i = 0; i < completed_opening.size(); i++){

            completed_opening.get(i).opening = true;
        }

        for (int i = 0; i < completed_closing.size(); i++){

            completed_closing.get(i).closing = true;
        }

        return;
    }


    public static Week generateSchedule(ArrayList<Employee> employee_list, ArrayList<LocalDate> date_range){

        // create list of employees
        // ArrayList<Employee> employee_list = create_employee_list();

        // create lists for employees that need opening and closing training.
        ArrayList<Employee> untrained_opening = create_untrained_opening(employee_list);
        ArrayList<Employee> untrained_closing = create_untrained_closing(employee_list);

        // create lists for employees that are trained in opening and closing.
        ArrayList<Employee> trained_opening = create_trained_opening(employee_list);
        ArrayList<Employee> trained_closing = create_trained_closing(employee_list);

        // create empty lists for employees that receive opening and closing training.
        ArrayList<Employee> completed_opening = new ArrayList<Employee>();
        ArrayList<Employee> completed_closing = new ArrayList<Employee>();

        // create empty list for employees who have not yet worked in the given week.
        ArrayList<Employee> not_worked = new ArrayList<Employee>();

        // fill not_worked list with all employees.
        for (int i = 0; i < employee_list.size(); i++){

            not_worked.add(employee_list.get(i));
        }

        // create new Week object.
        Week test_week = create_week(date_range);

        // print employee list before schedule is created, to show training status for each employee.
        //System.out.println("Employee list before schedule was generated:");
        //print_employees(employee_list);

        // run first pass: employees that need opening training.
        opening_training_check(test_week, untrained_opening, completed_opening, not_worked);

        // run second pass: employees that need closing training.
        closing_training_check(test_week, untrained_closing, completed_closing, not_worked);

        // run third pass: opening shift supervisors.
        opening_supervisor_check(test_week, trained_opening, not_worked);

        // run fourth pass: closing shift supervisors.
        closing_supervisor_check(test_week, trained_closing, not_worked);

        // run fifth pass: add remaining employees who have not worked to empty shifts.
        not_worked_check(test_week, not_worked);

        // run sixth pass: fill in remaining shifts with random workers.
        empty_shift_check(test_week, employee_list);

        // update employee training status.
        // !! update_training(completed_opening, completed_closing);

        return test_week;
    }
}

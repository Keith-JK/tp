package common;

import command.AssignmentCommand;
import command.CalendarCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.SearchCommand;

/**
 * Container for all default messages printed to user.
 */
public class Messages {
    // Start up and Exit Print Messages
    public static final String LOGO =
            " _______        _______        _______        _______ "
                    + System.lineSeparator()
                    + "|   _   |      |       |      |   _   |      |       |"
                    + System.lineSeparator()
                    + "|  |_|  |      |_     _|      |  |_|  |      |  _____|"
                    + System.lineSeparator()
                    + "|       |        |   |        |       |      | |_____ "
                    + System.lineSeparator()
                    + "|       | ___    |   |   ___  |       | ___  |_____  |"
                    + System.lineSeparator()
                    + "|   _   ||   |   |   |  |   | |   _   ||   |  _____| |"
                    + System.lineSeparator()
                    + "|__| |__||___|   |___|  |___| |__| |__||___| |_______|"
                    + System.lineSeparator();
    public static final String EXIT_MESSAGE = "Exiting A.T.A.S";

    // Common Print Messages
    public static final String DIVIDER = "_______________________________________________________________________";
    public static final String NEWLINE_INDENT = "     ";
    public static final String COMMENTS_INDENT = "            notes: ";
    public static final String REPEAT_EVENT_WITH_COMMENTS_INDENT = "      [%s]  notes: ";
    public static final String PROMPT_FOR_USER_INPUT = "> ";

    // Help Print Messages
    public static final String DATE_FORMAT_HELP = "Date Format: dd/MM/yy HHmm";
    public static final String START_END_DATE_FORMAT_HELP = "Date Format: dd/MM/yy HHmm - HHmm";

    // Command Print Messages
    public static final String ADD_SUCCESS_MESSAGE = "Added task:" + System.lineSeparator() + NEWLINE_INDENT
            + "%s" + System.lineSeparator() + "Now you have %d task%s in the list!";
    public static final String EMPTY_TASKLIST_MESSAGE = "No tasks were found";
    public static final String NO_TODAY_TASKS_MESSAGE = "You have no tasks for today!";
    public static final String SHOW_TODAY_TASKS_MESSAGE = "Here are the tasks you have for today";
    public static final String SHOW_TASKLIST_MESSAGE = "Here are the relevant tasks:%s%s";
    public static final String DONE_SUCCESS_MESSAGE = "[%s] has been marked done!";
    public static final String DELETE_SUCCESS_MESSAGE = "[%s] has been deleted!";
    public static final String CLEAR_SUCCESS_MESSAGE = "All tasks have been deleted";
    public static final String CLEAR_DONE_SUCCESS_MESSAGE = "All completed tasks have been removed";
    public static final String SEARCH_SUCCESS_MESSAGE = "Here are the search results:";
    public static final String EDIT_SUCCESS_MESSAGE = "Task edited successfully:" + System.lineSeparator()
            + NEWLINE_INDENT + "%s.";
    public static final String ADD_REPEATING_SUCCESS_MESSAGE = "[%s] will repeat every %d %s%s.";
    public static final String REMOVE_REPEATING_SUCCESS_MESSAGE = "[%s] will no longer repeat.";
    public static final String EDIT_PROMPT = "Please edit your chosen task.";

    // Others
    public static final String NO_TASKS_MSG = "You have no tasks at the moment";
    public static final String RANGE_OF_VALID_TASK_INDEX_MSG = "1 to %1$s";

    // Error Messages
    public static final String INCORRECT_COMMAND_ERROR = "Oh no. %s";
    public static final String UNKNOWN_COMMAND_ERROR = "Unknown command entered";
    public static final String DATE_INCORRECT_OR_INVALID_ERROR = "Wrong date format or invalid date provided"
            + System.lineSeparator() + DATE_FORMAT_HELP;
    public static final String START_END_DATE_INCORRECT_OR_INVALID_ERROR = "Wrong date format or invalid date provided"
            + System.lineSeparator() + START_END_DATE_FORMAT_HELP;
    public static final String NUM_FORMAT_ERROR = "Please provide an integer as the command parameter";
    public static final String INVALID_ID_ERROR = "Please provide a valid task number from %1$s";
    public static final String COMPLETED_TASK_ERROR = "Task is already completed";
    public static final String REPEAT_TASK_ERROR = "Please use a different name. Task already exists in list";
    public static final String EMPTY_DONE_CLEAR_ERROR = "There are no completed tasks at the moment";
    public static final String INCORRECT_START_END_TIME_ERROR = "The end time should come after the start time";
    public static final String INCORRECT_STORAGE_FORMAT_ERROR = "The local save file is of an unknown format. "
            + "Exit now using <Ctrl C> to manually fix the save file, "
            + "or the save file will be overwritten with the new session data";
    public static final String NO_SAVE_FILE_MESSAGE = "No existing save file found. A new save file will be created";
    public static final String SAVE_FAILED_MESSAGE = "Oh no. Something went wrong while saving, please try again later";

    public static final String ASSIGN_INCORRECT_FORMAT_ERROR = "Incorrect format for Assignment Command"
            + System.lineSeparator() + AssignmentCommand.COMMAND_USAGE;
    public static final String EVENT_INCORRECT_FORMAT_ERROR = "Incorrect format for Event Command"
            + System.lineSeparator() + EventCommand.COMMAND_USAGE;
    public static final String LIST_INCORRECT_FORMAT_ERROR = "Invalid argument for List Command";
    public static final String DONE_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Done Command"
            + System.lineSeparator() + DoneCommand.COMMAND_USAGE;
    public static final String DELETE_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Delete Command"
            + System.lineSeparator() + DeleteCommand.COMMAND_USAGE;
    public static final String CLEAR_INCORRECT_FORMAT_ERROR = "Invalid argument for Clear Command";
    public static final String EMPTY_SEARCH_RESULTS_ERROR = "There are no matching tasks for the search query";
    public static final String INVALID_SEARCH_FORMAT = "Invalid Argument for Search Command";
    public static final String SEARCH_INSUFFICIENT_ARGS = "Insufficient argument for Search Command"
            + System.lineSeparator() + SearchCommand.COMMAND_USAGE;
    public static final String INVALID_REPEAT_ERROR = "Please choose a valid index.";
    public static final String INVALID_EVENT_REPEAT_ERROR = "Please choose an event.";
    public static final String REPEAT_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Repeat Command";
    public static final String CALENDAR_INCORRECT_FORMAT_ERROR = "Incorrect format for Calendar Command"
            + System.lineSeparator() + CalendarCommand.COMMAND_USAGE;

}

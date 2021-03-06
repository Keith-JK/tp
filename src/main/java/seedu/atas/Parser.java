package seedu.atas;

import command.AssignmentCommand;
import command.CalendarCommand;
import command.ClearCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.EditCommand;
import command.EventCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.RepeatCommand;
import command.SearchCommand;
import common.Messages;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    public static final DateTimeFormatter PRINT_DATE_FORMAT = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH':'mm");
    public static final DateTimeFormatter PRINT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH':'mm");
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");

    private static final String COMMAND_TYPE_FIELD = "command";
    private static final String TASK_NAME_FIELD = "taskName";
    private static final String MODULE_NAME_FIELD = "moduleName";
    private static final String LOCATION_FIELD = "location";
    private static final String DATE_TIME_FIELD = "dateTime";
    private static final String COMMENTS_FIELD = "comments";
    private static final String TASK_TYPE_FIELD = "taskType";
    private static final String TASK_INDEX_FIELD = "index";
    private static final String NUM_OF_PERIOD_FIELD = "numOfPeriod";
    private static final String TYPE_OF_PERIOD_FIELD = "typeOfPeriod";
    private static final String CALENDAR_DATE_FIELD = "calendarDate";

    // regex for an add assignment command
    public static final Pattern ASSIGNMENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<" + COMMAND_TYPE_FIELD + ">(?i)"
                    + AssignmentCommand.COMMAND_WORD
                    + "\\b)"
                    + "\\s+n/\\s*(?<" + TASK_NAME_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+m/\\s*(?<" + MODULE_NAME_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+d/\\s*(?<" + DATE_TIME_FIELD + ">\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
                    + "\\s+c/\\s*(?<" + COMMENTS_FIELD + ">[^|/\\s]+[^|/]*)$"
    );

    // regex for an add event command
    public static final Pattern EVENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<" + COMMAND_TYPE_FIELD + ">(?i)"
                    + EventCommand.COMMAND_WORD
                    + "\\b)"
                    + "\\s+n/\\s*(?<" + TASK_NAME_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+l/\\s*(?<" + LOCATION_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+d/\\s*(?<" + DATE_TIME_FIELD + ">\\d{2}/\\d{2}/\\d{2}\\s+\\d{4}\\s*-\\s*\\d{4})"
                    + "\\s+c/\\s*(?<" + COMMENTS_FIELD + ">[^|/\\s]+[^|/]*)$"
    );

    //regex for search command
    public static final Pattern SEARCH_PARAMETERS_FORMAT = Pattern.compile(
            "(?<" + COMMAND_TYPE_FIELD + ">(?i)"
                    + SearchCommand.COMMAND_WORD
                    + "\\b)"
                    + "\\s+t/\\s*(?<" + TASK_TYPE_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+n/\\s*(?<" + TASK_NAME_FIELD + ">[^|/\\s]+[^|/]*)");

    //regex for Searchd command
    public static final Pattern SEARCHD_PARAMETERS_FORMAT = Pattern.compile(
            "(?<" + COMMAND_TYPE_FIELD + ">(?i)"
                    + SearchCommand.dCOMMAND_WORD
                    + "\\b)"
                    + "\\s+t/\\s*(?<" + TASK_TYPE_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+n/\\s*(?<" + TASK_NAME_FIELD + ">[^|/\\s]+[^|/]*)"
                    + "\\s+d/\\s*(?<" + DATE_TIME_FIELD + ">\\d{2}/\\d{2}/\\d{2})");

    //regex for repeat command
    public static final Pattern REPEAT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<" + COMMAND_TYPE_FIELD + ">(?i)"
                    + RepeatCommand.COMMAND_WORD
                    + "\\b)"
                    + "\\s+id/\\s*(?<" + TASK_INDEX_FIELD + ">\\d+)"
                    + "\\s+p/\\s*(?<" + NUM_OF_PERIOD_FIELD + ">\\d+)"
                    + "(?<" + TYPE_OF_PERIOD_FIELD + ">[dwmy])?");

    //regex for calendar command
    public static final Pattern CALENDAR_PARAMETERS_FORMAT = Pattern.compile(
            "(?<" + COMMAND_TYPE_FIELD + ">(?i)"
                    + CalendarCommand.COMMAND_WORD
                    + "\\b)"
                    + "\\s+d/\\s*(?<" + CALENDAR_DATE_FIELD + ">\\d{2}/\\d{2})");

    //@@author lwxymere
    /**
     * Returns a Command object depending on the command input by the user.
     *
     * @param fullCommand line input by the user, which represents a command
     * @return Command object depending on user input, with the appropriate arguments set
     */
    public static Command parseCommand(String fullCommand) {
        String commandType = fullCommand.split("\\s+", 2)[0].trim().toLowerCase();

        switch (commandType) {
        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand(fullCommand);
        case AssignmentCommand.COMMAND_WORD:
            return prepareAssignmentCommand(fullCommand);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(fullCommand);
        case ClearCommand.COMMAND_WORD:
            return prepareClearCommand(fullCommand);
        case DoneCommand.COMMAND_WORD:
            return prepareDoneCommand(fullCommand);
        case EventCommand.COMMAND_WORD:
            return prepareEventCommand(fullCommand);
        case ListCommand.COMMAND_WORD:
            return prepareListCommand(fullCommand);
        case SearchCommand.COMMAND_WORD:
            return prepareSearchCommand(fullCommand);
        case SearchCommand.dCOMMAND_WORD:
            return prepareSearchdCommand(fullCommand);
        case EditCommand.COMMAND_WORD:
            return prepareEditCommand(fullCommand);
        case RepeatCommand.COMMAND_WORD:
            return prepareRepeatCommand(fullCommand);
        case CalendarCommand.COMMAND_WORD:
            return prepareCalendarCommand(fullCommand);
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand(fullCommand);
        default:
            return new IncorrectCommand(Messages.UNKNOWN_COMMAND_ERROR);
        }
    }

    /**
     * Returns a LocalDateTime object based on an input String with the format INPUT_DATE_FORMAT.
     *
     * @param dateTimeString String representing a date with the format dd/MM/yy HHmm
     * @return LocalDateTime representing the date and time specified in dateTimeString
     * @throws DateTimeParseException    if dateTimeString does not follow INPUT_DATE_FORMAT
     * @throws IndexOutOfBoundsException if dateTimeString does not follow INPUT_DATE_FORMAT
     */
    public static LocalDateTime parseDate(String dateTimeString)
            throws DateTimeParseException, IndexOutOfBoundsException {
        // handle issue where there are multiple spaces between the date and the time
        String[] dateAndTime = dateTimeString.split("\\s+", 2);
        String formattedDateTimeString = dateAndTime[0] + " " + dateAndTime[1];
        return LocalDateTime.parse(formattedDateTimeString, INPUT_DATE_TIME_FORMAT);
    }

    private static Command prepareAssignmentCommand(String fullCommand) {
        final Matcher matcher = ASSIGNMENT_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                    capitalize(AssignmentCommand.COMMAND_WORD), AssignmentCommand.COMMAND_USAGE));
        }

        LocalDateTime dateTime;
        try {
            dateTime = parseDate(matcher.group(DATE_TIME_FIELD));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(Messages.DATE_INCORRECT_OR_INVALID_ERROR);
        }

        String assignmentName = capitalize(matcher.group(TASK_NAME_FIELD).replaceAll("\\s+", " ").trim());
        String moduleName = matcher.group(MODULE_NAME_FIELD).replaceAll("\\s+", " ").trim();
        String comments = capitalize(matcher.group(COMMENTS_FIELD).replaceAll("\\s+", " ").trim());
        return new AssignmentCommand(assignmentName, moduleName, dateTime, comments);
    }

    //@@author joelczk
    private static Command prepareSearchCommand(String fullCommand) {
        final Matcher matcher = SEARCH_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    capitalize(SearchCommand.COMMAND_WORD), SearchCommand.COMMAND_USAGE));
        }
        String taskType = matcher.group(TASK_TYPE_FIELD).toLowerCase().trim();
        String taskName = matcher.group(TASK_NAME_FIELD).trim();
        return new SearchCommand(taskName, taskType, null);
    }

    private static Command prepareSearchdCommand(String fullCommand) {
        final Matcher matcher = SEARCHD_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    capitalize(SearchCommand.dCOMMAND_WORD), SearchCommand.dCOMMAND_USAGE));
        }
        String taskType = matcher.group(TASK_TYPE_FIELD).toLowerCase().trim();
        String taskName = matcher.group(TASK_NAME_FIELD).trim();
        String stringDate = matcher.group(DATE_TIME_FIELD).trim();
        LocalDate date;
        try {
            date = LocalDate.parse(stringDate, INPUT_DATE_FORMAT);
            return new SearchCommand(taskName, taskType, date);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(String.format(Messages.SEARCHD_DATE_INVALID,
                    Messages.SEARCHD_DATE_FORMAT_HELP));
        }
    }

    //@@author lwxymere
    private static Command prepareDeleteCommand(String fullCommand) {
        String[] tokens = fullCommand.split("\\s+", 2);
        assert tokens.length == 1 || tokens.length == 2;
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.NUM_FORMAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    capitalize(DeleteCommand.COMMAND_WORD), DeleteCommand.COMMAND_USAGE));
        }
        return new DeleteCommand(deleteIndex);
    }

    private static Command prepareDoneCommand(String fullCommand) {
        String[] tokens = fullCommand.split("\\s+", 2);
        assert tokens.length == 1 || tokens.length == 2;
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.NUM_FORMAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    capitalize(DoneCommand.COMMAND_WORD), DoneCommand.COMMAND_USAGE));
        }
        return new DoneCommand(doneIndex);
    }

    private static Command prepareEventCommand(String fullCommand) {
        final Matcher matcher = EVENT_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                    Parser.capitalize(EventCommand.COMMAND_WORD), EventCommand.COMMAND_USAGE));
        }

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        try {
            String startEndDateTime = matcher.group(DATE_TIME_FIELD);
            String[] dateTimeTokens = startEndDateTime.split("\\s+", 2);
            String[] timeTokens = dateTimeTokens[1].split("-", 2);
            startDateTime = parseDate(dateTimeTokens[0] + " " + timeTokens[0].trim());
            endDateTime = parseDate(dateTimeTokens[0] + " " + timeTokens[1].trim());
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(Messages.START_END_DATE_INCORRECT_OR_INVALID_ERROR);
        }

        if (!endDateTime.isAfter(startDateTime)) {
            return new IncorrectCommand(Messages.INCORRECT_START_END_TIME_ERROR);
        }

        String eventName = capitalize(matcher.group(TASK_NAME_FIELD).replaceAll("\\s+", " ").trim());
        String location = matcher.group(LOCATION_FIELD).replaceAll("\\s+", " ").trim();
        String comments = capitalize(matcher.group(COMMENTS_FIELD).replaceAll("\\s+", " ").trim());
        return new EventCommand(eventName, location, startDateTime, endDateTime, comments);
    }

    //@@author e0309556
    private static Command prepareListCommand(String fullCommand) {
        String[] tokens = fullCommand.trim().split("\\s+", 2);
        if (tokens.length == 1) {
            // check if list has no parameters
            return new ListCommand(null);
        }
        assert tokens.length == 2;
        return new ListCommand(tokens[1].replaceAll("\\s+", " ").trim());
    }

    //@@author joelczk
    private static Command prepareClearCommand(String fullCommand) {
        String[] tokens = fullCommand.trim().split("\\s+", 2);
        if (tokens.length == 1) {
            return new ClearCommand(null);
        }
        assert tokens.length == 2;
        return new ClearCommand(tokens[1].replaceAll("\\s+", " ").trim());
    }

    //@@author
    private static Command prepareExitCommand(String fullCommand) {
        assert fullCommand.trim().equals(ExitCommand.COMMAND_WORD);
        if (fullCommand.equals(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        } else {
            return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                    capitalize(ExitCommand.COMMAND_WORD), ExitCommand.COMMAND_USAGE));
        }
    }

    private static Command prepareHelpCommand(String fullCommand) {
        assert fullCommand.trim().equals(HelpCommand.COMMAND_WORD);
        if (fullCommand.equals(HelpCommand.COMMAND_WORD)) {
            return new HelpCommand();
        } else {
            return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                    capitalize(HelpCommand.COMMAND_WORD), HelpCommand.COMMAND_USAGE));
        }
    }

    //@@author jichngan
    private static Command prepareEditCommand(String fullCommand) {
        String[] tokens = fullCommand.split("\\s+", 2);
        int editIndex;
        try {
            editIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.NUM_FORMAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    capitalize(EditCommand.COMMAND_WORD), EditCommand.COMMAND_USAGE));
        }
        return new EditCommand(editIndex);
    }

    //@@author e0309556
    private static Command prepareRepeatCommand(String fullCommand) {
        final Matcher matcher = REPEAT_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                    capitalize(RepeatCommand.COMMAND_WORD), RepeatCommand.COMMAND_USAGE));
        }
        try {
            int eventIndex = Integer.parseInt(matcher.group(TASK_INDEX_FIELD)) - 1;
            int numOfPeriod = Integer.parseInt(matcher.group(NUM_OF_PERIOD_FIELD));
            String typeOfPeriod = matcher.group(TYPE_OF_PERIOD_FIELD);
            if (numOfPeriod != 0 && typeOfPeriod == null) {
                return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                        capitalize(RepeatCommand.COMMAND_WORD), RepeatCommand.COMMAND_USAGE));
            }
            return new RepeatCommand(eventIndex, numOfPeriod, typeOfPeriod);
        } catch (NumberFormatException e) {
            //Error will be caught by Matcher from the regex above
            assert false;
            return new IncorrectCommand(Messages.NUM_FORMAT_ERROR);
        }
    }

    //@@author Keith-JK
    private static Command prepareCalendarCommand(String fullCommand) {
        final Matcher matcher = CALENDAR_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.INCORRECT_FORMAT_ERROR,
                    capitalize(CalendarCommand.COMMAND_WORD), CalendarCommand.COMMAND_USAGE));
        }

        LocalDate date;
        try {
            date = LocalDate.parse(
                    String.format("01/%s", matcher.group(CALENDAR_DATE_FIELD).trim()), INPUT_DATE_FORMAT);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(String.format(Messages.CALENDAR_DATE_INVALID, CalendarCommand.COMMAND_USAGE));
        }

        return new CalendarCommand(date);
    }

    /**
     * Capitalizes the first alphabet of a string.
     * @param str String to be capitalized
     * @return Capitalized string
     */
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

# Student Database

## Project specification

Create an application that takes the following input from a NSU Student
   1. Name according to NSU ID
   2. 7 Digit Student ID
   3. School (Use a dropdown list)
      1. This dropdown list should be defined in the resources section and not hardcoded. 
   4. Department (Use a dropdown list)
      1. This dropdown list should be defined in the resources section.
      2. Department list should be specific to each school.
   5. Date of Birth
      1. Use a date picker
   6. Phone number
      1. Should be Bangladeshi phone number format
   7. NID number
      1. NID number should be in the new format.
      2. If the number does not match NID number format (10 digit), a error message should be displayed.
   8. Present Address
      1. Country
      2. District
      3. Post Office
      4. Police Station
      5. Postal Code
      6. House/Village/City
      7. Road/Block/Sector
   9. Permanent Address
      1. Country
      2. District
      3. Post Office
      4. Police Station
      5. Postal Code
      6. House/Village/City
      7. Road/Block/Sector

   2. Divide the input into multiple activities. Each activity should have a forward and back button. When the user clicks on submit button, Save the input data into a local SQL database using Room. (https://developer.android.com/training/data-storage/room)

   3. Add another activity showing vertical list of 7 digit student IDs using RecyclerView. This list should be extracted from the SQL database using Room. When one item on the list is tapped, the Name and Department of the student should be shown in a popup message.

   4. Add another activity with advanced search feature. This activity allows you to filter students matching a set of criteria extracted from the above input. You have the flexibility to decide how you want to implement this search feature. 

   5. Add Bangla language support for this app for every label, button and options. This can be done from here : [Language Support](https://developer.android.com/training/basics/supporting-devices/languages)

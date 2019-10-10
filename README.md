# EmDevCharCounter

This program is designed to process text files. It calculates the number of words in the text, finds unique letters and calculates the number of occurrences of each of them. The program is optimized for Windows, was written in IntelliJ Idea and does not use any third-party builders.

<b>Some features of the work:</b>

After starting, the program processes all text files in the "IN" folder in the root directory of the project. After processing all available files, the program goes into standby mode. If any of the files changes, or a new text file appears, the program starts the calculation for the changed file.

To read files, the encoding "Windows-1251" is set for support the work with Cyrillic alphabet in Windows.
The calculation results of each file are written to a text file with the template name "result_for_% FILENAME" into the folder "OUT".

All events related to working with files are displayed in the console.

The program is in an endless loop when the standby mode is running. Therefore, it is necessary to interrupt the program manually if it is necessary to complete it.

The name of the input folder, output folder and the encoding for reading files can be changed in the code in the "main" method of the "Main" class upon the first call of the "ThisSession" object.

Team BitOpt Readme

Team Members

Anthony Harris
Connor Wallace
Tommy Herz
Blake Powell
Pablo Perez

Pitch & Description

Pitch

BitOpt is an application that optimizes your mining system based on hourly updated cryptocurrency values. This application takes a significant amount of computational work away from the miner and provides automated statistics to aid in achieving the miner’s profit gain.

Description

BitOpt will read the past monthly average value of the selected cryptocurrency, the updated value on the hour, and will calculate the deviation between both values. Using this deviation, your mining system’s compute capacity can be optimized to the suggested percentage.

Known Issues

Portfolio View

Formatting of the list view isn’t perfect, some displayed values are not aligned with other values that are displayed

Even if the user chooses a Euro value of currency, labels and text will still show “$.” Please disregard that symbol when using Euro values.

Eclipse throws some errors and they are displayed in red font in the Eclipse console. But, the app still works the way that it should. Please disregard the following errors:
Eclipse generates seemingly harmless errors when performing an add or remove coin transaction
Eclipse generates seemingly harmless errors when the user does not select an account and/or has a transaction amount error

Double transaction in Portfolio View

Add and remove coin operations in the app will result in duplicate transaction of the same amount and type when:
After the view is first initialized in the app, the user selects a currency, the user enters a desired transaction amount, and the user performs an add or remove operation
Any time the user selects alternative consecutive transactions – i.e. the user selects “add coin”, then “remove coin”, then “add coin” again…etc

The BitOpt developers are spinning this bug to be an incentive feature called the “Decisive Cryptocurrency Proliferation Program”
We want our users to help support the expansion of cryptocurrency by investing in cryptocurrency. So, the developers will match an equivalent value of the first transaction they make every time they enter the portfolio view of the app. For example, if the user adds $10 in bitcoin, we will match it. 
However, the opposite can occur. If the user decides to take away from the cryptocurrency market in their first transaction they make every time he/she enters the portfolio view, then the developers will charge the user a processing fee equivalent to the amount they want to withdraw.
Finally, the developers want to promote decisive thinking in the cryptocurrency market. Therefore, if the user makes alternate and consecutive add and remove transactions, the developers will also charge the user a processing fee equivalent to the amount they want to withdraw.

Portfolio view unstable opening
The portfolio.fxml became corrupted numerous times in development. The view had to be created from scratch several times. And, as a protective measure, the developer made several copies of portfolio.fxml in case one of the other copies became corrupted. 
Some developers could open the view, and some couldn’t through Scenebuilder and/or while the app was running.
Portfolio3.fxml and portfolio4.fxml are redundant copies of each other just in case one of them becomes corrupted. Both have been tested to open successfully in scenebuilder and in the app.

Demo Information

Portfolio View

Portfolio General info

The user will see:
The name of the currency 
Current and last balances
Drop downs to choose a cryptocurrency account and transaction choice (add/remove coin)
A text field to input the amount to be transacted
A view on the bottom to show recent transactions

Initial portfolio view

When the user initially enters the portfolio view in the app, the app is defaulted to show bitcoin USD information

Using the Portfolio portion of the app

Each currency will have the elements stated in “General info”
For proper operation, the user must enter/choose in order:
Either the desired account selection from the drop down and a transaction amount
Then, choose a transaction type from the drop down
If the proper operation is not followed, a red error label in the top right of the GUI will display



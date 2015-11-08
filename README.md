Android Survey
==========================

A simple to use, survey library for collecting feedback from your users.


![alt text](https://github.com/AndreiD/surveylib/raw/master/app/surveygif.gif "Android Survey Gif")


### Instalation:

Until I figure out this jcenter() stuff, you'll have to do it manually.
**Fork this project**

### How to use it:

1. Take a look at the [example project](https://github.com/AndreiD/surveylib/blob/master/app/src/main/java/androidadvance/com/androidsurveyexample/MainActivity.java)


##### Step 1:

You will need:
A json file with the questions. Check the [assets folder](https://github.com/AndreiD/surveylib/tree/master/app/src/main/assets) to see 3 examples.


##### Step 2:

Call the class **SurveyActivity** and pass as an extra the json string.
~~~~
        private static final int SURVEY_REQUEST = 1337;
        a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_1.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });
~~~~



What is loadSurveyJson ? check [this function.](https://github.com/AndreiD/surveylib/blob/master/app/src/main/java/androidadvance/com/androidsurveyexample/MainActivity.java#L77)


##### Step 3:

The activity is started with a request code. 

~~~~
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SURVEY_REQUEST) {
            if (resultCode == RESULT_OK) {

                String answers_json = data.getExtras().getString("answers");
                Log.d("****", "****************** WE HAVE ANSWERS ******************");
                Log.v("ANSWERS JSON", answers_json);
                Log.d("****", "*****************************************************");

                //do whatever you want with them...
            }
        }
    }
~~~~

##### Last step:

Add this activity to your manifest file.
~~~~
<activity android:name="com.androidadvance.androidsurvey.SurveyActivity"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
            android:windowSoftInputMode="stateHidden"/>
~~~~

##### The Json format of the Questions.

You have couple of question types: String, StringMultiline, Number, Radioboxes, Checkboxes. They can be "required" or not, the choices can be random. Check the examples, everything is simple to use.


##### Customizations

###### Colors
Use html codes in your json question title, choices. You can see a [compiled list here](http://commonsware.com/blog/Android/2010/05/26/html-tags-supported-by-textview.html)

Define your material design colors in the style. 

Not enough ? You have to do it yourself. Simplest way is to [Fork this project](https://github.com/AndreiD/surveylib#fork-destination-box) and style, add, modify who you like.

###### More question types / Other Stuff

Fork this project

#### Troubleshooting 

1. Make sure you have the ***latest*** shit. At this moment: compileSdkVersion 23, targetSdkVersion 23, buildToolsVersion "23.0.1", compile 'com.android.support:appcompat-v7:23.1.0',   compile 'com.android.support:design:23.1.0' etc.
2. Feed a valid json! Otherwise you'll get errors.
3. Check the sample project.

#### Updates, Questions, and Requests

Ping me here :)


#### TODO://

* Waiting for your suggestions


#### You like this library ? Check my other projects.

- https://github.com/AndreiD/UltimateAndroidAppTemplate
- https://github.com/AndreiD/TSnackBar

### If you use this library, please star this project.

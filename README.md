
# BusSpy

This program helps to look and trace all bus routes in the city. It
uses service https://go2bus.ru and it can be used with minimum
information traffic over the net where the service does not work well.

---

This is a general description how to build and install the program.

This program is an Android Studio project files for building an
Android application.

1. You need an installed Linux system and you need to install Android
Studio to the Linux system.

2. After the environment installation you should run Android Studio
and create a project in Android Studio with name `BusSpy`.

3. In the directory of this project `BusSpy` you should initialize a
new repository and then you should add a remote origin for this
repository on GitHub.

4. After the setting up the remote repository you should pull the
repository from the added remote origin. After that you will see
several new directories in the project `BusSpy` directory.

5. After the pulling the remote repository you should run file
`configure` in the `BusSpy` directory. Then it will make file
`Makefile` in the project `BusSpy` directory.

6. After the creation of file `Makefile` you should run `make`. Then
all `BusSpy` project files will be updated by according repository
files.

7. After the updating of repository files you should build the
application in Android Studio. The application package will appear in
the release directory.

8. After the application building you should put the package file of
the application to the smartphone or another device.

9. After the uploading of the application you should install the
application to the smartphone from the application package in a
smartphone directory. You will see the icon of application `BusSpy` in
the smartphone.

10. After that you should run the application `BusSpy` in the
    smartphone.

---

### Requirements


This program has tested on environment configuration
```
- Linux Fedora 26
- Android Studio 4.0.1
- Android 7.1.1

```

In Android Studio you should check following settings

1. Select menu `Tools`.

2. Select submenu `SDK Manager`.

3. Select tab `SDK Platforms`.

There should be installed

```
- Android SDK Platform 30
- Android SDK Platform 29
- Sources for Android 29
- Android SDK Platform 28
- Sources for Android 28
```

1. Select menu `Tools`.

2. Select submenu `SDK Manager`.

3. Select tab `SDK Tools`.

There should be installed

```
- Android SDK Build-Tools 30.0.1
- Android SDK Build-Tools 29.0.2
- Android SDK Build-Tools 28.0.2
```

### Building

#### Pre-build

1. Run Android Studio.

2. Press `Start a new Android Studio project`.

3. Select `Phone and Tablet` and `Basic Activity`.

4. Select fields. Name `BusSpy`. Package name `com.example.busspy`. Language `Java`. Minimum SDK `API 16: Android 4.1 (Jelly Bean)`. Use legacy android.support libraries `empty`.

5. Open the Linux console in the `BusSpy` directory.

6. Run commands

```sh
$ git init
$ git remote add origin git@github.com:freeprogs/android-busspy.git
$ git pull origin master
$ ./configure
$ make
```

####Build

1. Select menu item `Build`.

2. Select submenu item `Generate Signed Bundle / APK`.

3. Select radio button `APK`.

4. Create the keys file. Create the password. Create the key alias.

5. Select `release` and check on `Signature Versions: V1(Jar Signature)` and check on `Signature Versions: V2(Full APK Signature)`.

6. Press next and build the application.

7. Check the file in directory `BusSpy` and path `app/release/app-release.apk` .


### Installation

#### Installation through a cable

1. Connect a cable to the smartphone and computer.

2. Allow douwnload through the cable in the smartphone.

3. Upload the apk-file to the smartphone in a directory.

4. Run installation of the apk-file in the smartphone.

5. Allow one installation from the untrusted source.

#### Installation through Internet

1. Put the apk-file to an Internet service program (E-Mail, any
   messenger and etc.)

2. Download this apk-file from the Internet service to smartphone in a
   directory.

3. Run installation of the apk-file in the smartphone.

5. Allow one installation from the untrusted source.

### Run

Search the icon of the BusSpy application in the smartphone or your
device and press the icon.

---

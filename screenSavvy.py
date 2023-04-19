import pyodbc
from flask import Flask, render_template, request, redirect, url_for, session
import re
import bcrypt
import os
import sys
import datetime
import pandas as pd


#connection string to connect to our database. Will have to change accordingly after db is created 
conn_str = ("Driver={ODBC Driver 17 for SQL Server};"
            "Server=DESKTOP-H17S9H6;"
            "Database=ScreenSavvy;"
            "Trusted_Connection=yes;")
conn = pyodbc.connect(conn_str)
cursor = conn.cursor()

app = Flask(__name__)
app.secret_key = 'your secret key'

#code for the landing page 
#Code for the login part. 
#code for the home page (page after login. need 2 (one for admin and one for customer))
#code for register 
#code for logout 
#code for change pw also have. Will add later. 

@app.route('/login', methods=['GET', 'POST'])
def login():
    msg = ''
    if request.method == 'POST' and 'username' in request.form and 'password' in request.form:
        username = request.form['username']
        password = request.form['password'].encode()
        cursor.execute("SELECT * FROM UserDetails WHERE username = ?" , (username, ))
        # Compare the hashed password
        account = cursor.fetchall()
        if account:
            stored_password = account[0][1]
            if bcrypt.checkpw(password, stored_password):
                print("Authentication successful")
                session['logged_in'] = True
                session['username'] = account[0][0]

                if session['username'] == 'admin':
                    return redirect(url_for('adm_records'))

                return redirect(url_for('home'))
            else:
                print("Authentication failed")
                msg = 'Incorrect username/password!'
        else: 
            msg = 'Incorrect username/password!'
    return render_template('index.html', msg=msg)

#code for the page displayed after login 

@app.route('/home',methods=['GET', 'POST'])
def home():
    if 'logged_in' in session:
        if session['username'] != 'admin':
            msg =''
        username = session['username']
        # We need all the account info for the user so we can display it on the profile page
        cursor.execute('SELECT * FROM UserDetails WHERE username = ?', (username,))
        account = cursor.fetchone()
        # Show the profile page with account info
        return render_template('profile.html', account=account, msg=msg)
    # User is not loggedin redirect to login page
    return redirect(url_for('login'))
    # User is not loggedin redirect to login page

# http://localhost:5000/python/logout - this will be the logout page
@app.route('/logout')
def logout():
    # Remove session data, this will log the user out
   session.pop('loggedin', None)
   session.pop('logged_in', None)
   session.pop('id', None)
   session.pop('username', None)
   session.pop('email', None)
   # Redirect to login page
   return redirect(url_for('login'))

@app.route('/register', methods=['GET', 'POST'])
def register():
    msg = ''
    if request.method == 'POST' and 'username' in request.form and 'password' in request.form and 'email' in request.form:

        password = request.form['password'].encode()
        salt = bcrypt.gensalt()
        global hashed 
        hashed = bcrypt.hashpw(password, salt)
        username = request.form['username']
        #password = request.form['password']
        email = request.form['email']
        phone = request.form['phone']

        cursor.execute('SELECT * FROM UserDetails WHERE username = ?', (username,))
        account = cursor.fetchone()
        cursor.execute('select * from UserDetails where email = ?', (email,))
        emailAccount = cursor.fetchone()
        # If account exists show error and validation checks
        if account:
            msg = 'Account already exists!'
        elif emailAccount: 
            msg = 'email already exists'
        elif not re.match(r'[^@]+@[^@]+\.[^@]+', email):
            msg = 'Invalid email address!'
        elif not re.match(r'[A-Za-z0-9]+', username):
            msg = 'Username must contain only characters and numbers!'
        elif not username or not password or not email:
            msg = 'Please fill out the form!'
        else:
            # Account doesnt exists and the form data is valid, now insert new account into accounts table
                status = 'active'
                cursor.execute('INSERT INTO UserDetails VALUES (?, ?, ?, ?, ?)', (username, hashed, email,phone,status))
                conn.commit()

                msg = 'You have successfully registered! Proceed to sign in'
                return redirect(url_for('login'))
            

    else: 
        # Form is empty... (no POST data)
        msg = 'Please fill out the form!'
    # Show registration form with message (if any)
    return render_template('register.html', msg=msg)
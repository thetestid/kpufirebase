from flask import Flask, render_template             #플라스크 모듈 호출
import RPi.GPIO as GPIO
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(4,GPIO.OUT,initial=0)

app = Flask(__name__)               #플라스크 앱 생성        

@app.route('/')                     #기본('/') 웹주소로 요청이 오면                     
def hello():                        #hello 함수 실행
    return render_template('index.html')      #// render_template함수 지정


@app.route("/relay/on")
def relay_on():
    try:
        GPIO.output(4,1)
        return "ok"
    except expression as identifier:
        return "fail"

@app.route("/relay/off")
def relay_off():
    try:
        GPIO.output(4,0)
        return "ok"
    except expression as identifier:
        return "fail"


if __name__ == '__main__':          #현재 파일 실행시 개발용 웹서버 구동
    app.run(debug=True, port=80, host='0.0.0.0')  

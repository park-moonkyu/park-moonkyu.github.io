# 대원CTS 예측 시스템

## 실행 정보

```bash
$ # SSH 정보
$ ssh -l root -p 1024 27.96.129.211

$ # 관리자 정보
$ root / tjswjd2021!@

$ # NAVER Cloud Platform 계정 정보
$ shin9353@computer.co.kr / eodnjscom2021!!

$ # 홈페이지 admin 로그인 정보
$ admin / P@ssw0rd1!

$ #실행 코드
$ python3 manage.py runserver 0:8000
```
http://101.101.219.158:8000

서버 관리
```bash

# 설치(CentOS)
~$ sudo yum install tmux

# tmux 실행
~$ tmux

# tmux log off
~$ control +c 후 d 

# 세션 다시 시작
$ tmux attach -t <session-number or session-name>


```
#### session(세션) : 
tmux가 관리하는 가장 큰 실행 단위다. tmux는 세션에 attach/detach 할 수 있다. tmux가 detach 한 세션은 종료되지 않고 백그라운드에서 실행을 계속 할 수 있다. 세션은 여러 윈도우(Window)로 구성된다. 
<br>
#### window(윈도우) : 
사용자가 보는 터미널 화면, 세션에서 여러 개의 윈도우가 탭처럼 존재한다. 세션에서 윈도우를 전환하면 새로운 윈도우로 화면이 전환된다. 
<br>
#### pane (팬) : 

하나의 윈도우를 분할한 단위. 윈도우 하나를 여러번 분할해서 여러개의 팬을 갖게 할 수 있다. 가로 혹은 세로로 화면을 분할해가면서 팬을 생성한다. 윈도우를 전환하면 팬 구성도 새로운 윈도우의 구성으로 전환된다.<br><br>

## 메뉴
- Dashboard : 예측 결과를 표시
- Visualization : 예측할 데이터를 시각화
- Pre-Processing : 예측할 데이터를 전저리
- Forecasting : 시각화/전처리에서 업로드한 데이터가 있다면 일/주/월 단위, 모델, 데이터(시각화/전처리) 선택하여 예측

<br />

## API 목록
| api                          |      기능      |         input        |        output       |
|------------------------------|---------------|----------------------|---------------------|
| /forecast/visual-file        | 시각화 파일 업로드 |                      |                     |
| /forecast/preprcs-file       | 전처리 파일 업로드 |                      |                     |
| /forecast/visualization      | 시각화 데이터 생성 |     forecast.xlsx    | forecast_result.xls |
| /forecast/visualization/data | 시각화 데이터 얻기 |                      |                     |
| /forecast/preprocessing      | 전처리(null 제거) 데이터 생성 |        eda.xlsx      |   eda_result.xlsx   |
| /forecast/preprocessing/inter | 전처리(보간법) 데이터 생성 |        eda.xlsx      |   eda_result.xlsx   |
| /forecast/preprocessing/data | 전처리 데이터 얻기 |                      |                     |
| /forecast/forecasting        |    예측 분석     |                      |  prophet_result.xlsx, arima_result.xlsx |
| /forecast/forecasting/data   | 예측 분석 결과 얻기 |                     |                     |

<br />

### /forecast/visual-file, /forecast/preprcs-file

> request

| param         |      type      |  requierd  |        description       |
|----------------|----------------|------------|--------------------------|
| fileObj        |    fileObject  |    true    |   시각화 업로드 파일 (엑셀)    |

<br />

> response

none

<br />

### /forecast/visualization, /forecast/preprocessing, /forecast/preprocessing/inter

> request

none

<br />

> response

| param         |      type      |  requierd  |        description       |
|----------------|----------------|------------|--------------------------|
| result        |    boolean  |    true    |   결과 성공/실패    |

<br />

### /forecast/visualization/data, /forecast/preprocessing/data

> request

none

<br />

> response

| param         |      type      |  requierd  |        description       |
|----------------|----------------|------------|--------------------------|
| result        |    boolean  |    true    |   결과 성공/실패    |
| count_ds        |    int  |    true    |   날짜 개수    |
| count_y        |    int  |    true    |   y값 개수    |
| null        |    int  |    true    |   null 개수    |
| mean        |    int  |    true    |  평균  |
| std        |    double  |    true    |  표준편차 |
| min        |    double  |    true    |  최소 |
| max        |    double  |    true    |  최대 |
| data        |    array  |    true    |  데이터 배열|
| data[index].ds        |    date  |    true    |  data[index] 날짜 |
| data[index].y        |    double  |    true    | data[index] y |

<br />

### /forecast/forecasting

> request

| param         |      type      |  requierd  |        description       |
|----------------|----------------|------------|--------------------------|
| input_data        |    string  |    true    |   a (시각화 데이터) / b (전처리 데이터)    |
| duration        |    string  |    true    |   D (일) / W (주) / M (월)    |
| day        |    int  |    true    |   예측할 일/주/월 수    |
| model        |    string  |    true    |   p (Prophet) / a (ARIMA)   |

<br />

> response

| param         |      type      |  requierd  |        description       |
|----------------|----------------|------------|--------------------------|
| result        |    boolean  |    true    |   결과 성공/실패    |

<br />

### /forecast/forecasting/data

> request

none

<br />

> response

| param         |      type      |  requierd  |        description       |
|----------------|----------------|------------|--------------------------|
| result        |    boolean  |    true    |   결과 성공/실패    |
| created_at        |    date  |    true    |   실행 날짜   |
| model        |    string  |    true    |   prophet / arima   |
| duration        |    int  |    true    |   D (일) / W (주) / M (월)    |
| day        |    int  |    true    |   예측 일/주/월 수    |
| input_data        |    string  |    true    |   a (시각화 데이터) / b (전처리 데이터)    |
| mean        |    int  |    true    |  평균  |
| mean_lower        |    int  |    true    |  lower 평균  |
| mean_upper        |    int  |    true    |  upper 평균  |
| std        |    double  |    true    |  표준편차 |
| max        |    double  |    true    |  최대 |
| max_lower        |    double  |    true    |  lower 최대 |
| max_upper        |    double  |    true    |  upper 최대 |
| data        |    array  |    true    |  데이터 배열 |
| data[index].ds        |    date  |    true    |  data[index] 날짜 |
| data[index].yhat        |    double  |    true    | data[index] y ||
| data[index].yhat_lower        |    double  |    true    | data[index] yhat_lower ||
| data[index].yhat_upper        |    double  |    true    | data[index] yhat_upper ||
| data[index].trend        |    double  |    true    | data[index] trend ||
| data[index].weekly_lower        |    double  |    true    | data[index] weekly_lower ||
| data[index].weekly_upper        |    double  |    true    | data[index] weekly_upper |

<br />


## Dashboard (결과)

> 기능

- 결과 엑셀 다운로드

<br />

> 데이터 값

- 실행일시
- 모델
- 데이터
- 단위
- 데이터 개수
- 평균
- 표준편차
- 최대값

<br />

> 그래프

- 데이터 그래프
- 요일별 합계 그래프
- 월별 합계 그래프

<br />

> 테이블

- y값 상위 3개 데이터 테이블
- y값 하위 3개 데이터 테이블

<br />

## 시각화/전처리

> 기능

- 결과 엑셀 다운로드
- 전처리: null 제거/보간법

<br />

> 데이터 값

- 데이터 날짜 범위 (2021.1.1 ~ 2020.12.31)
- 데이터 개수
- 최소값
- 최대값
- 평균
- 표준편차
- null 값 개수

<br />

> 그래프

- 데이터 그래프(선/막대)
- 요일별 합계
- 월별 합계 그래프
<br>

<br />

> 테이블

- y값 상위 3개 데이터 테이블
- y값 하위 3개 데이터 테이블

<br />

### Code-base structure

The project is coded using a simple and intuitive structure presented bellow:

```bash
< PROJECT ROOT >
   |
   |-- core/                               # Implements app logic and serve the static assets
   |    |-- settings.py                    # Django app bootstrapper
   |    |-- wsgi.py                        # Start the app in production
   |    |-- urls.py                        # Define URLs served by all apps/nodes
   |    |
   |    |-- static/
   |    |    |-- <css, JS, images>         # CSS files, Javascripts files
   |    |    |-- assets/js/pages
   |    |        |- dashboard-main.js       # Dashboard js file
   |    |        |- forecast.js             # Forecast js file
   |    |        |- pre-processing.js       # Pre-processing js file
   |    |        |- visualication.js        # Visualication js file
   |    |
   |    |-- templates/                     # Templates used to render pages
   |         |
   |         |-- includes/                 # HTML chunks and components
   |         |    |-- navigation.html      # Top menu component
   |         |    |-- sidebar.html         # Sidebar component
   |         |    |-- footer.html          # App Footer
   |         |    |-- scripts.html         # Scripts common to all pages
   |         |
   |         |-- layouts/                  # Master pages
   |         |    |-- base-fullscreen.html # Used by Authentication pages
   |         |    |-- base.html            # Used by common pages
   |         |
   |         |-- accounts/                 # Authentication pages
   |         |    |-- login.html           # Login page
   |         |    |-- register.html        # Register page
   |         |
   |      index.html                       # The default page
   |      page-404.html                    # Error 404 page
   |      page-500.html                    # Error 404 page
   |      *.html                           # All other HTML pages
   | 
   |      visualization.html               # Visualization HTML pages
   |      pre_processing.html              # Pre-Processing HTML pages
   |      forecasting.html                 # Forecasting HTML pages
   |
   |-- authentication/                     # Handles auth routes (login and register)
   |    |
   |    |-- urls.py                        # Define authentication routes  
   |    |-- views.py                       # Handles login and registration  
   |    |-- forms.py                       # Define auth forms  
   |
   |-- app/                                # A simple app that serve HTML files
   |    |
   |    |-- views.py                       # Serve HTML pages for authenticated users
   |    |-- urls.py                        # Define some super simple routes  
   |
   |-- forecast/                           # Forecast app logic (backend)
   |    |
   |    |-- views.py                       # Serve forecasting
   |    |-- urls.py                        # Define forecast routes  
   |
   |-- mk/                                 # Forecast logic (backend)
   |    |
   |    |-- pre_processing.py              # Pre-precessing logic
   |    |-- prophet.py                     # Prophet logic
   |    |-- visualization.py               # Visualization logic
   |
   |-- requirements.txt                    # Development modules - SQLite storage
   |
   |-- .env                                # Inject Configuration via Environment
   |-- manage.py                           # Start the app - Django default start script
   |
   |-- ************************************************************************
```


<br />
<br />


## Dashboard 에 있으면 좋은 것들 

> 기능

- 결과 엑셀 다운로드

<br />

> 데이터 값

- 실제 값 최소값 Top 3
- 실제 값 최대값 Top 3
- 예측 값 최대값 top 3  <-- 아직 구현 X
- 예측 값 최대값 top 3  <-- 아직 구현 X
- 전체 최소값 top 3   <-- 아직 구현 X
- 전체 최대값 top 3    <-- 아직 구현 X
- 정확도 <-- 아직 구현 X

<br />

---
# 사용 라이브러리

## [Gradient Able](https://appseed.us/admin-dashboards/django-gradient-able) 



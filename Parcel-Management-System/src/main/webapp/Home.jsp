<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Developer Home</title>
    <link rel="stylesheet" type="text/css" href="styles/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">

    <!-- Add FontAwesome for icons -->
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(120deg, #3498db, #8e44ad);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        
        .container {
            text-align: center;
            background-color: white;
            border-radius: 10px;
            padding: 40px;
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
            animation: fadeIn 1.5s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: scale(0.8); }
            to { opacity: 1; transform: scale(1); }
        }

        h1 {
            color: #2c3e50;
            font-weight: 500;
            margin-bottom: 20px;
            animation: slideIn 1s ease-out;
        }

        @keyframes slideIn {
            from { transform: translateY(-50px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        .buttons {
            margin-top: 20px;
        }

        .buttons a {
            text-decoration: none;
            color: white;
            padding: 10px 25px;
            background-color: #3498db;
            border-radius: 5px;
            font-size: 16px;
            margin: 0 10px;
            transition: background-color 0.3s ease-in-out;
        }

        .buttons a:hover {
            background-color: #2980b9;
        }

        .buttons a.register-btn {
            background-color: #9b59b6;
        }

        .buttons a.register-btn:hover {
            background-color: #8e44ad;
        }

        /* Floating background animation */
        .floating-box {
            position: absolute;
            top: -50px;
            left: 10%;
            width: 100px;
            height: 100px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
            animation: float 4s infinite ease-in-out;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(50px); }
        }

        /* Add more floating elements for a cool effect */
        .floating-box:nth-child(2) { top: 300px; left: 70%; width: 150px; animation-duration: 6s; }
        .floating-box:nth-child(3) { top: 150px; left: 40%; width: 120px; animation-duration: 5s; }
    </style>
</head>
<body>

    <div class="floating-box"></div>
    <div class="floating-box"></div>
    <div class="floating-box"></div>

    <div class="container">
        <h1>Welcome Developer!</h1>
        <p>Select an option to continue:</p>
        <div class="buttons">
            <a href="register.jsp" class="register-btn"><i class="fas fa-user-plus"></i> Register</a>
            <a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
        </div>
    </div>

</body>
</html>

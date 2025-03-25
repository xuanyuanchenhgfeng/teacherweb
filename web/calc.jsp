<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>复数运算器</title>
</head>
<body>
<h2>复数运算器</h2>
<form action="" method="post">
    <label for="c1_real">复数1 实部:</label>
    <input type="number" id="c1_real" name="c1_real" required><br><br>

    <label for="c1_imaginary">复数1 虚部:</label>
    <input type="number" id="c1_imaginary" name="c1_imaginary" required><br><br>

    <label for="c2_real">复数2 实部:</label>
    <input type="number" id="c2_real" name="c2_real" required><br><br>

    <label for="c2_imaginary">复数2 虚部:</label>
    <input type="number" id="c2_imaginary" name="c2_imaginary" required><br><br>

    <label for="operation">运算类型:</label>
    <select id="operation" name="operation" required>
        <option value="addition">加法</option>
        <option value="subtraction">减法</option>
        <option value="multiplication">乘法</option>
        <option value="division">除法</option>
    </select><br><br>

    <input type="submit" value="计算">
</form>

<%-- 处理表单提交和复数运算 --%>
<%
    if (request.getMethod().equals("POST")) {
        double c1_real = Double.parseDouble(request.getParameter("c1_real"));
        double c1_imaginary = Double.parseDouble(request.getParameter("c1_imaginary"));
        double c2_real = Double.parseDouble(request.getParameter("c2_real"));
        double c2_imaginary = Double.parseDouble(request.getParameter("c2_imaginary"));
        String operation = request.getParameter("operation");

        // 定义复数类
        class Complex {
            double real;
            double imaginary;

            public Complex(double real, double imaginary) {
                this.real = real;
                this.imaginary = imaginary;
            }

            public Complex add(Complex other) {
                return new Complex(this.real + other.real, this.imaginary + other.imaginary);
            }

            public Complex subtract(Complex other) {
                return new Complex(this.real - other.real, this.imaginary - other.imaginary);
            }

            public Complex multiply(Complex other) {
                double realPart = this.real * other.real - this.imaginary * other.imaginary;
                double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
                return new Complex(realPart, imaginaryPart);
            }

            public Complex divide(Complex other) {
                double denominator = Math.pow(other.real, 2) + Math.pow(other.imaginary, 2);
                double realPart = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
                double imaginaryPart = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
                return new Complex(realPart, imaginaryPart);
            }
        }

        Complex complex1 = new Complex(c1_real, c1_imaginary);
        Complex complex2 = new Complex(c2_real, c2_imaginary);

        Complex result;
        String operationText = "+";
        switch (operation) {
            case "addition":
                operationText = " + ";
                result = complex1.add(complex2);
                break;
            case "subtraction":
                operationText = " - ";
                result = complex1.subtract(complex2);
                break;
            case "multiplication":
                operationText = " * ";
                result = complex1.multiply(complex2);
                break;
            case "division":
                operationText = " / ";
                result = complex1.divide(complex2);
                break;
            default:
                out.println("<p style='color:red;'>Invalid operation</p>");
                return;
        }

        // 输出结果
        out.println("<h3>运算结果:</h3>");
        out.println("<p> (" + complex1.real + " + " + complex1.imaginary + "i ) " + operationText  + " (" + complex2.real + " + " + complex2.imaginary + "i)"  + "=(" + result.real + " + " + result.imaginary + "i)</p>");
    }
%>
</body>
</html>
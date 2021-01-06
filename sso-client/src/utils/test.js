export function testPhoneNum(self, phoneNum) {
    // 验证手机号
    let reg = /^1[0-9]{10}$/;
    if (phoneNum == "") {
        self.$message({
            message: "手机号不能为空",
            type: "warning",
        });
        return false;
    } else if (phoneNum.length <= 10 || !reg.test(phoneNum)) {
        self.$message({
            message: "手机号格式不正确",
            type: "warning",
        });
        return false;
    }
    return true;
}
export function testPassWord(self, value) {
    if (value == "") {
        self.$message({
            message: "密码不能为空",
            type: "warning",
        });
        return false;
    }
    return true;
}
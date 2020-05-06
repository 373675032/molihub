 var month_olypic = [31,29,31,30,31,30,31,31,30,31,30,31];//闰年每个月份的天数
        var month_normal = [31,28,31,30,31,30,31,31,30,31,30,31];
        var month_name =["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
        //获取以上各个部分的id
        var holder = document.getElementById("days");
        var prev = document.getElementById("prev");
        var next = document.getElementById("next");
        var ctitle = document.getElementById("calendar-title");
        var cyear = document.getElementById("calendar-year");
        //获取当天的年月日
        var my_date = new Date();
        var my_year = my_date.getFullYear();//获取年份
        var my_month = my_date.getMonth(); //获取月份，一月份的下标为0
        var my_day = my_date.getDate();//获取当前日期

        //根据年月获取当月第一天是周几
        function dayStart(month,year){
            var tmpDate = new Date(year, month, 1);
            return (tmpDate.getDay());
        }
        //根据年份判断某月有多少天(11,2018),表示2018年12月
        function daysMonth(month, year){
            var tmp1 = year % 4;
            var tmp2 = year % 100;
            var tmp3 = year % 400;

            if((tmp1 == 0 && tmp2 != 0) || (tmp3 == 0)){
                return (month_olypic[month]);//闰年
            }else{
                return (month_normal[month]);//非闰年
            }
        }
        //js实现str插入li+class,不要忘了用innerhtml进行插入
        function refreshDate(){
            var str = "";
            //计算当月的天数和每月第一天都是周几，day_month和day_year都从上面获得
            var totalDay = daysMonth(my_month,my_year);
            var firstDay = dayStart(my_month, my_year);
            //添加每个月的空白部分
            for(var i = 0; i < firstDay; i++){
                str += "<li>"+"</li>";
            }

            //从一号开始添加知道totalDay，并为pre，next和当天添加样式
            var myclass;
            for(var i = 1; i <= totalDay; i++){
                if((my_year < my_date.getFullYear())||(my_year == my_date.getFullYear() && my_month < my_date.getMonth()) || (my_year == my_date.getFullYear() && my_month == my_date.getMonth() && i < my_day)){
                    myclass = " class='lightgrey'";
                }else if(my_year == my_date.getFullYear() && my_month == my_date.getMonth() && i == my_day){
                    myclass = "class = 'green greenbox'";
                }else{
                    myclass = "class = 'darkgrey'";
                }
                str += "<li "+myclass+">"+i+"</li>";
            }
            holder.innerHTML = str;
            ctitle.innerHTML = month_name[my_month];
            cyear.innerHTML = my_year;
        }
        //调用refreshDate()函数，日历才会出现
        refreshDate();
        //实现onclick向前或向后移动
        prev.onclick = function(e){
            e.preventDefault();
            my_month--;
            if(my_month < 0){
                my_year--;
                my_month = 11; //即12月份
            }
            refreshDate();
        }

        next.onclick = function(e){
            e.preventDefault();
            my_month++;
            if(my_month > 11){
                my_month = 0;
                my_year++;
            }
            refreshDate();
        }

// function test(){
// 	alert("你好");
// }

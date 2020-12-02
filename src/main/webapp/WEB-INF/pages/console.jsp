<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>控制台主页</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md8">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">快捷方式</div>
						<div class="layui-card-body">

							<div class="layui-carousel layadmin-carousel layadmin-shortcut">
								<div carousel-item>
									<ul class="layui-row layui-col-space10">
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/student/query">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>学生信息查询</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/teacher/query">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>教师信息查询</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/class/query">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>班级信息查询</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/major/query">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>专业&学院信息查询</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/user/info">
												<i class="layui-icon layui-icon-user"></i>
												<cite>个人资料</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/user/password">
												<i class="layui-icon layui-icon-set"></i>
												<cite>修改密码</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/user/email">
												<i class="layui-icon layui-icon-set"></i>
												<cite>修改邮箱</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/student/modify">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>学生信息修改</cite>
											</a>
										</li>
									</ul>
									<ul class="layui-row layui-col-space10">
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/teacher/modify">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>教师信息修改</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/class/modify">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>班级信息修改</cite>
											</a>
										</li>
										<li class="layui-col-xs3">
											<a lay-href="${ctx}/major/modify">
												<i class="layui-icon layui-icon-survey"></i>
												<cite>专业&学院信息修改</cite>
											</a>
										</li>
									</ul>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">所有学院师资力量对比</div>
						<div class="layui-card-body">
							<div id="container3" class="layui-carousel layadmin-carousel layadmin-dataview" style="height: 100%">
								123
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">学生男女比概览</div>
						<div class="layui-card-body">
							<div id="container" class="layui-carousel layadmin-carousel layadmin-dataview" style="height: 100%">
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">学院学生人数比概览</div>
						<div class="layui-card-body">
							<div id="container2" class="layui-carousel layadmin-carousel layadmin-dataview" style="height: 100%">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-col-md4">
			<div class="layui-card">
				<div class="layui-card-header">版本信息</div>
				<div class="layui-card-body layui-text">
					<table class="layui-table">
						<colgroup>
							<col width="100">
							<col>
						</colgroup>
						<tbody>
						<tr>
							<td>当前版本</td>
							<td>
								<script type="text/html" template>
									SIMS-SSM-v1.0
								</script>
							</td>
						</tr>
						<tr>
							<td>前端框架</td>
							<td>
								<script type="text/html" template>
									Layui-v2.4.5
								</script>
							</td>
						</tr>
						<tr>
							<td>后端框架</td>
							<td>
								<script type="text/html" template>
									Spring+SpringMVC+MyBatis
								</script>
							</td>
						</tr>
						<tr>
							<td>数据库</td>
							<td>
								<script type="text/html" template>
									MySQL-v5.5.61
								</script>
							</td>
						</tr>
						<tr>
							<td>开发环境</td>
							<td>
								<script type="text/html" template>
									java-1.8 | win10-x64 | IDEA-2019
								</script>
							</td>
						</tr>
						<tr>
							<td>主要特色</td>
							<td>响应式 / 清爽 / 极简 / 沙雕</td>
						</tr>
						<tr>
							<td>项目地址</td>
							<td style="padding-bottom: 0;">
								<div class="layui-btn-container">
									<a href="https://github.com/yoyling/sims-ssm" target="_blank"
									   class="layui-btn layui-btn-danger">项目介绍</a>
									<a href="https://github.com/yoyling/sims-ssm"
									   target="_blank" class="layui-btn">GitHub</a>
								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="layui-card">
				<div class="layui-card-header">
					更新公告
					<i class="layui-icon layui-icon-tips" lay-tips="注意查看哦" lay-offset="5"></i>
				</div>
				<div class="layui-card-body layui-text layadmin-text">
					<c:forEach items="${notifies}" var="notify" varStatus="n">
						<p style="text-indent:0em;">${notify.notifyDate.toLocaleString()}<br>
						<font style="color: #009688">${notify.notifyContent}</font>
					</c:forEach>
				</div>
			</div>
			<div class="layui-card">
				<div class="layui-card-header">
					作者心语
					<i class="layui-icon layui-icon-tips" lay-tips="要支持的噢" lay-offset="5"></i>
				</div>
				<div class="layui-card-body layui-text layadmin-text">
					<p>面向教学信息以及学生及老师的信息管理要求，学生信息管理系统应运而生。
						它能提供线上信息管理，高效处理数据。方便对学生及老师各类数据进行大数据分析。
					<p>该平台接入正方2.0版本的教务系统，现能直接使用<a href="http://jw.xmut.edu.cn/"
					                              target="_blank">厦门理工学院教务系统</a>的学生帐号登录并自动获取学生信息以及成绩等数据。
					<p>已在GitHub开源此项目，切勿用于商业用途，感谢。</p>
					<p>—— YOYLING.（<a href="https://yoyling.com/" target="_blank">yoyling.com</a>）</p>
				</div>
			</div>
		</div>

	</div>
</div>

<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js?t=1"></script>
<script src="${ctx}/static/plugins/layuiadmin/lib/extend/echarts.js"></script>

<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/echarts-gl.min.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/ecStat.min.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/dataTool.min.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/china.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/world.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/bmap.min.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/vendors/simplex.js"></script>--%>
<%--<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=HAyXKM0od6KqNdGCqwmx07WPm111ZF5B&__ec_v__=20190126"></script>--%>

<script>
	layui.config({
		base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'console', 'form'], function () {
		var $ = layui.$
			, form = layui.form;

		$.ajax({
			async: false,
			type: "get",
			url: "${ctx}/findSexPercent",
			success: function (res) {
				var dom = document.getElementById("container");
				var myChart = echarts.init(dom);
				var app = {};
				option = null;

				var piePatternImg = new Image();
				piePatternImg.src = '${ctx}/static/custom/images/bg1.png';
				var bgPatternImg = new Image();
				bgPatternImg.src = '${ctx}/static/custom/images/bg2.png';

				var itemStyle = {
					normal: {
						opacity: 0.7,
						color: {
							image: piePatternImg,
							repeat: 'repeat'
						},
						borderWidth: 3,
						borderColor: '#235894'
					}
				};
				option = {
					backgroundColor: {
						image: bgPatternImg,
						repeat: 'repeat'
					},
					title: {
						text: '全校学生男女比',
						subtext: '总人数:' + res.studentPercent.total,
						textStyle: {
							color: '#235894'
						}
					},
					tooltip: {},
					series: [{
						name: '性别',
						type: 'pie',
						selectedMode: 'single',
						selectedOffset: 30,
						clockwise: true,
						label: {
							normal: {
								textStyle: {
									fontSize: 18,
									color: '#235894'
								}
							}
						},
						labelLine: {
							normal: {
								lineStyle: {
									color: '#235894'
								}
							}
						},
						data: [
							{value: res.studentPercent.male, name: '男'},
							{value: res.studentPercent.female, name: '女'}
						],
						itemStyle: itemStyle
					}]
				};
				;
				if (option && typeof option === "object") {
					myChart.setOption(option, true);
				}
			}
		});

		$.ajax({
			async: false,
			type: "get",
			data: {
				type: "allCollege"
			},
			url: "${ctx}/findPersonTotalPercentByCommonName",
			success: function (res) {
				console.log(res);
				var dom = document.getElementById("container2");
				var myChart = echarts.init(dom);
				var app = {};
				option = null;
				app.title = '坐标轴刻度与标签对齐';

				option = {
					color: ['#FFB800'],
					tooltip: {
						trigger: 'axis',
						axisPointer: {            // 坐标轴指示器，坐标轴触发有效
							type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					xAxis: [
						{
							type: 'category',
							data: res.commonName,
							axisTick: {
								alignWithLabel: true
							}
						}
					],
					yAxis: [
						{
							type: 'value'
						}
					],
					series: [
						{
							name: '人数',
							type: 'bar',
							barWidth: '50%',
							data: res.total
						}
					]
				};
				if (option && typeof option === "object") {
					myChart.setOption(option, true);
				}

			}
		});

		$.ajax({
			async: false,
			type: "get",
			data: {
				type: "allCollegeByTeaTitle"
			},
			url: "${ctx}/findTeaTotalGroupByTitle",
			success: function (res) {
				var dom = document.getElementById("container3");
				var myChart = echarts.init(dom);
				var app = {};
				option = null;
				option = {
					legend: {},
					tooltip: {},
					dataset: {
						source: res.source
					},
					xAxis: [
						{type: 'category', gridIndex: 0},
						{type: 'category', gridIndex: 1}
					],
					yAxis: [
						{gridIndex: 0},
						{gridIndex: 1}
					],
					grid: [
						{bottom: '55%'},
						{top: '55%'}
					],
					series: res.series
				};
				;
				if (option && typeof option === "object") {
					myChart.setOption(option, true);
				}
			}
		});
	});

</script>
</body>
</html>



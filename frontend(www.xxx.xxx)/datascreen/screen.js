var myChart1, myChart2;

function initializeChart1() {
  var chartDom = document.getElementById('main');
  myChart1 = echarts.init(chartDom, 'dark');
  var option = {
    title: {
      text: '用户性别比例',
      subtext: '数据来源于后台',
      left: 'center',
      top: 'top',
      textStyle: {
        color: '#ffffff',
        fontSize: 18
      },
      subtextStyle: {
        color: '#cccccc',
        fontSize: 14
      }
    },
    legend: {
      top: 'bottom'
    },
    toolbox: {
      show: true,
      feature: {
        mark: { show: true },
        dataView: { show: true, readOnly: false },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    series: [
      {
        name: 'Nightingale Chart',
        type: 'pie',
        radius: [0, 100],
        center: ['50%', '50%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 8
        },
        data: [] // 初始化时设置为空数据
      }
    ]
  };
  myChart1.setOption(option);
}

function initializeChart2() {
  var chartDom = document.getElementById('main2');
  myChart2 = echarts.init(chartDom, 'dark');
  var option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
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
        data: ['注册总人数', '发帖总数'],
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
        name: '数量',
        type: 'bar',
        barWidth: '60%',
        data: [0, 0] // 初始化时设置为空数据
      }
    ]
  };
  myChart2.setOption(option);
}

function initializeChart3() {

}

function updateCharts(data) {
  // 更新饼图
  myChart1.setOption({
    series: [{
      data: [
        { value: data.nan, name: '男' },
        { value: data.nv, name: '女' },
        { value: data.null, name: '未定' }
      ]
    }]
  });

  // 更新柱状图
  myChart2.setOption({
    series: [{
      data: [data.usernumber, data.postsnumber]
    }]
  });
}

function updateCharts2(data) {
  //更新帖子的显示
  var container = document.getElementById('picture4');
  container.innerHTML = ''; // 清空旧内容

  data.forEach(post => {
    var postElement = document.createElement('div');
    postElement.className = 'post-item';

    var imgElement = document.createElement('img');
    imgElement.src = post.user_pic; // 用户头像 URL
    imgElement.alt = 'User Avatar';

    var contentElement = document.createElement('div');
    contentElement.className = 'content';

    var titleElement = document.createElement('div');
    titleElement.className = 'title';
    titleElement.textContent = post.title; // 帖子标题

    var likesElement = document.createElement('div');
    likesElement.className = 'likes';
    likesElement.textContent = `点赞数: ${post.likes}`; // 点赞数

    contentElement.appendChild(titleElement);
    contentElement.appendChild(likesElement);

    postElement.appendChild(imgElement);
    postElement.appendChild(contentElement);

    container.appendChild(postElement);
  });
}

function fetchData() {
  fetch('http://localhost:9090/largescreen/table1', {
    method: "GET",
  })
    .then(response => response.json())
    .then(data => {
      updateCharts(data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });

  fetch('http://localhost:9090/largescreen/table2', {
    method: "GET",
  })
    .then(response => response.json())
    .then(data => {
      updateCharts2(data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });

}

// 页面加载时初始化图表
document.addEventListener('DOMContentLoaded', () => {
  var pictureDiv1 = document.getElementById('picture1');
  pictureDiv1.innerHTML = '<div id="main"></div>';
  initializeChart1();

  var pictureDiv2 = document.getElementById('picture2');
  pictureDiv2.innerHTML = '<div id="main2"></div>';
  initializeChart2();

  fetchData(); // 初始数据加载
  //setInterval(fetchData, 2000); // 每2秒更新一次数据
});
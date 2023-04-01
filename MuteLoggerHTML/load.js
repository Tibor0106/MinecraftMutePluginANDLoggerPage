$(document).ready(function() {
    var offset = 10;
    var limit = 20;
    $('.container').scroll(function() {
      var div = document.querySelector(".container");
      if(div.scrollTop + div.clientHeight >= div.scrollHeight) {
        $.ajax({
          url: 'php/load-more.php',
          type: 'POST',
          data: { offset: offset, limit: limit },
          success: function(data) {
          offset += limit;
          data = [data];
          var datas = JSON.parse(data);
          const table = $('<table>');
          table.addClass('table');
          const tbody = $('<tbody>');
          datas.forEach((elem) => {
            const tr = $('<tr>');
            tr.append(`<td>${elem.player}</td>`);
            tr.append(`<td>${elem.staff}</td>`);
            tr.append(`<td>${elem.duration}</td>`);
            tr.append(`<td>${elem.reason}</td>`);
            tr.append(`<td>${elem.date}</td>`);
            $(".table").append(tr); 
          });         
        }
      });      
    }    
  });
});
function load() {
  $.ajax({
    url: 'php/load_data.php',
    type: 'POST',
    success: function(data) {
    $('.container').empty(); 
    data = [data];
    var datas = JSON.parse(data);
    const table = $('<table>');
    table.addClass('table');
    const thead = $('<thead>');
    const tr = $('<tr>');
    tr.append('<th>Player</th>');
    tr.append('<th>Staff</th>');
    tr.append('<th>Duration (seconds)</th>');
    tr.append('<th>Reason</th>');
    tr.append('<th>Date</th>');
    thead.append(tr);
    table.append(thead);
    const tbody = $('<tbody>');
    datas.forEach((elem) => {
      const tr = $('<tr>');
      tr.append(`<td>${elem.player}</td>`);
      tr.append(`<td>${elem.staff}</td>`);
      tr.append(`<td>${elem.duration}</td>`);
      tr.append(`<td>${elem.reason}</td>`);
      tr.append(`<td>${elem.date}</td>`);
      tbody.append(tr);
    });
    table.append(tbody);
    $('.container').append(table); 
  }
});
      
}
load();
var data_query;
function find() {
  
  let text = document.getElementById("find").value;
  if(text.length == 0){
    location.reload();
    return true;
  }
  $.ajax({
    url: 'php/find.php',
    type: 'POST',
    data: { find_m: text },       
    success: function(data) {
    $('.container').empty(); 
    data = [data];
    var datas = JSON.parse(data);
    const table = $('<table>');
    table.addClass('table');
    const thead = $('<thead>');
    const tr = $('<tr>');
    tr.append('<th>Player</th>');
    tr.append('<th>Staff</th>');
    tr.append('<th>Duration</th>');
    tr.append('<th>Reason</th>');
    tr.append('<th>Date</th>');
    thead.append(tr);
    table.append(thead);
    const tbody = $('<tbody>');
    datas.forEach((elem) => {
      const tr = $('<tr>');
      tr.append(`<td>${elem.player}</td>`);
      tr.append(`<td>${elem.staff}</td>`);
      tr.append(`<td>${elem.duration}</td>`);
      tr.append(`<td>${elem.reason}</td>`);
      tr.append(`<td>${elem.date}</td>`);
      tbody.append(tr);
    });
    table.append(tbody);
    $('.container').append(table); 
  }
  });
}
$('.reset').click(function() {
  document.getElementById("find").value = "";
  location.reload();
})
function stat(){
  $.ajax({
    url: 'php/stat.php',
    type: 'POST',   
    success: function(data) { 
    data = [data];
    var datas = JSON.parse(data);
    datas.forEach((i) => {
      var a = '<div class="m-5 stat-list-item"><h3 class="card-title"><b>Staff<br>'+i.staff+'</b><br></h3><p><b>Mutes</b><br>'+i.mute_count+'</p></div>';
      $('.stat-list').append(a);

    });  
  }
});
}
stat();

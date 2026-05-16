function initCharts() {
    const ctx1 = document.getElementById('salesChart');
    if (ctx1) new Chart(ctx1, { type: 'line', data: { labels: ['M','T','W','T','F','S','S'], datasets: [{ label: 'Sales ($)', data: [120,190,300,50,200,350,400], borderColor: 'rgb(75, 192, 192)', tension: 0.1 }] } });
    const ctx2 = document.getElementById('inventoryChart');
    if (ctx2) new Chart(ctx2, { type: 'doughnut', data: { labels: ['Elec','Acc','Furn'], datasets: [{ data: [30,50,20], backgroundColor: ['#FF6384','#36A2EB','#FFCE56'] }] } });
}
if(document.getElementById('salesChart')) initCharts();
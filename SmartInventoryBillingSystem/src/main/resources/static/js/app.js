function checkAuth() {
    const token = localStorage.getItem('token');
    const path = window.location.pathname;
    if (!token && path !== '/index.html' && path !== '/') window.location.href = 'index.html';
    if (token && (path === '/index.html' || path === '/')) window.location.href = 'dashboard.html';
}
function logout() { localStorage.removeItem('token'); window.location.href = 'index.html'; }

// Login
if (document.getElementById('loginForm')) {
    document.getElementById('loginForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const u = document.getElementById('username').value, p = document.getElementById('password').value;
        try {
            const data = await login(u, p);
            localStorage.setItem('token', data.token);
            window.location.href = 'dashboard.html';
        } catch (err) { document.getElementById('errorAlert').classList.remove('d-none'); }
    });
}

// Dashboard
async function loadDashboard() {
    try {
        const s = await apiCall('/dashboard/stats');
        document.getElementById('statProducts').innerText = s.totalProducts;
        document.getElementById('statRevenue').innerText = '$' + s.totalRevenue.toFixed(2);
        document.getElementById('statLowStock').innerText = s.lowStockCount;
        document.getElementById('statInvoices').innerText = s.totalInvoices;
    } catch(e){}
}

// Products
async function loadProducts() {
    const ps = await apiCall('/products');
    const tb = document.getElementById('productTableBody');
    tb.innerHTML = ps.map(p => `<tr><td>${p.id}</td><td>${p.name}</td><td>${p.sku}</td><td>$${p.price}</td><td>${p.stock}</td><td>${p.category}</td><td><button class="btn btn-sm btn-info" onclick='editProduct(${JSON.stringify(p)})'>Edit</button> <button class="btn btn-sm btn-danger" onclick="deleteProduct(${p.id})">Del</button></td></tr>`).join('');
}
async function saveProduct() {
    const id = document.getElementById('productId').value;
    const data = { name: document.getElementById('pName').value, sku: document.getElementById('pSku').value, price: parseFloat(document.getElementById('pPrice').value), stock: parseInt(document.getElementById('pStock').value), category: document.getElementById('pCategory').value };
    await apiCall(id ? `/products/${id}` : '/products', id ? 'PUT' : 'POST', data);
    bootstrap.Modal.getInstance(document.getElementById('productModal')).hide();
    loadProducts();
}
function prepareAddModal() { document.getElementById('productForm').reset(); document.getElementById('productId').value=''; }
function editProduct(p) {
    document.getElementById('productId').value=p.id; document.getElementById('pName').value=p.name; document.getElementById('pSku').value=p.sku; document.getElementById('pPrice').value=p.price; document.getElementById('pStock').value=p.stock; document.getElementById('pCategory').value=p.category; new bootstrap.Modal(document.getElementById('productModal')).show();
}
async function deleteProduct(id) { if(confirm('Sure?')) { await apiCall(`/products/${id}`,'DELETE'); loadProducts(); } }

// Customers
async function loadCustomers() {
    const cs = await apiCall('/customers');
    document.getElementById('customerTableBody').innerHTML = cs.map(c => `<tr><td>${c.name}</td><td>${c.email}</td><td>${c.phone}</td><td>${c.address}</td></tr>`).join('');
}
async function saveCustomer() {
    const d = { name: document.getElementById('cName').value, email: document.getElementById('cEmail').value, phone: document.getElementById('cPhone').value, address: document.getElementById('cAddress').value };
    await apiCall('/customers','POST',d);
    bootstrap.Modal.getInstance(document.getElementById('customerModal')).hide();
    loadCustomers();
}

// Billing
let cart=[];
async function loadBilling() {
    const [prods, custs] = await Promise.all([apiCall('/products'), apiCall('/customers')]);
    document.getElementById('invoiceProduct').innerHTML = prods.map(p => `<option value="${p.id}" data-price="${p.price}">${p.name} ($${p.price})</option>`).join('');
    document.getElementById('invoiceCustomer').innerHTML = custs.map(c => `<option value="${c.id}">${c.name}</option>`).join('');
    loadRecentInvoices();
}
function addToCart() {
    const pId = parseInt(document.getElementById('invoiceProduct').value);
    const qty = parseInt(document.getElementById('invoiceQty').value);
    const prod = (await apiCall('/products')).find(p => p.id === pId); // Simplified for demo, better cache
    // Re-fetching for simplicity in snippet, ideally use global array
    apiCall('/products').then(prods => {
        const product = prods.find(p => p.id === pId);
        const ex = cart.find(i => i.productId === pId);
        if (ex) { ex.quantity += qty; ex.totalPrice = ex.quantity * product.price; }
        else cart.push({productId: pId, productName: product.name, quantity: qty, unitPrice: product.price, totalPrice: qty*product.price});
        renderCart();
    });
}
function renderCart() {
    const tb = document.getElementById('cartBody');
    let t=0;
    tb.innerHTML = cart.map((i, idx) => { t+=i.totalPrice; return `<tr><td>${i.productName}</td><td>$${i.unitPrice}</td><td>${i.quantity}</td><td>$${i.totalPrice}</td><td><button class="btn btn-sm btn-danger" onclick="removeFromCart(${idx})">X</button></td></tr>`; }).join('');
    document.getElementById('grandTotal').innerText = t.toFixed(2);
}
function removeFromCart(i) { cart.splice(i,1); renderCart(); }
async function finalizeInvoice() {
    if(cart.length===0) return alert('Empty');
    await apiCall('/invoices','POST', {customerId: parseInt(document.getElementById('invoiceCustomer').value), items: cart});
    alert('Invoice Created'); cart=[]; renderCart(); loadRecentInvoices();
}
async function loadRecentInvoices() {
    const invs = await apiCall('/invoices');
    document.getElementById('recentInvoicesList').innerHTML = invs.slice(0,5).map(i => `<li class="list-group-item d-flex justify-content-between">${i.invoiceNumber} <a href="${'../..'+API_BASE}/invoices/${i.id}/download" class="btn btn-sm btn-primary">PDF</a></li>`).join('');
}

document.addEventListener('DOMContentLoaded', () => {
    checkAuth();
    if(document.getElementById('statProducts')) loadDashboard();
    if(document.getElementById('productTableBody')) loadProducts();
    if(document.getElementById('customerTableBody')) loadCustomers();
    if(document.getElementById('invoiceCustomer')) loadBilling();
});
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header style={{ padding: '1rem', backgroundColor: '#f0f0f0', borderBottom: '1px solid #ccc' }}>
      <nav>
        <Link to="/" style={{ marginRight: '1rem', fontWeight: 'bold' }}>AIMS</Link>
        <Link to="/products" style={{ marginRight: '10px' }}>Sản phẩm</Link>
        <Link to="/cart" style={{ marginRight: '10px' }}>Giỏ hàng</Link>
        <Link to="/login">Đăng Nhập</Link>
      </nav>
    </header>
  );
}
export default Header;
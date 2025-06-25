import { Outlet } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';

function AppLayout() {
  return (
    <div>
      <Header />
      <main style={{ padding: '2rem' }}>
        {/* Outlet sẽ là nơi hiển thị nội dung của các trang con */}
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}
export default AppLayout;
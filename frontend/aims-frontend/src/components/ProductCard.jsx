import React from 'react';

// CSS trực tiếp trong file để tiện lợi
const cardStyles = {
  border: '1px solid #ddd',
  borderRadius: '8px',
  padding: '1rem',
  margin: '1rem',
  width: '250px',
  boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-between'
};

const buttonStyles = {
  backgroundColor: '#007bff',
  color: 'white',
  border: 'none',
  padding: '0.5rem 1rem',
  borderRadius: '4px',
  cursor: 'pointer',
  marginTop: '1rem'
};


function ProductCard({ product, imageSrc }) {
  return (
    <div style={cardStyles}>
      <img src={imageSrc} alt={product.title} style={{ width: '100%', height: '200px', objectFit: 'cover' }} />
      <h3>{product.title}</h3>
      <p>{product.category}</p>
      <p>{product.price.toLocaleString('vi-VN')} VND</p>
      <button style={buttonStyles}>Thêm vào giỏ</button>
    </div>
  );
}

export default ProductCard;
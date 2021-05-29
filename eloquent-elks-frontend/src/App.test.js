import { render, screen } from '@testing-library/react';
import App from './App';

test('renders recommendation button', () => {
  render(<App />);
  const linkElement = screen.getByText(/Load recommendation/i);
  expect(linkElement).toBeInTheDocument();
});

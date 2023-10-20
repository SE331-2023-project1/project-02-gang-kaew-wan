import type { Config } from 'tailwindcss'
import forms from '@tailwindcss/forms'

export default {
  content: ['./index.html', './src/**/*.vue'],
  theme: {
    extend: {
      keyframes: {
        yellowfade: {
          '0%': { opacity: '1.0' },
          '100%': { background: 'transparent', opacity: '0.0' }
        }
      },
      animation: {
        flashMessage: 'yellowfade 3s ease-in-out'
      }
    }
  },
  plugins: [forms]
} satisfies Config

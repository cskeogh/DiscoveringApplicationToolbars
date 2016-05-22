from scipy import stats
import matplotlib.pyplot as plt

control_total_time = [120.791, 42.026, 121.395, 150.696, 88.358, 83.241, 105.375]

res = stats.probplot(control_total_time, plot=plt)
plt.title('Normal Probabiliy Plot - Total Time, Control')
plt.show()

exp_total_time = [117.566, 114.851, 78.324, 45.895, 96.253, 79.668 ]
res = stats.probplot(exp_total_time, plot=plt)
plt.title('Normal Probabiliy Plot - Total Time, Experimental')
plt.show()

print(stats.ttest_ind(control_total_time, exp_total_time))

control_hover = [46.489, 15.818, 25.598, 78.423, 36.847, 23.947, 32.979 ]

res = stats.probplot(control_hover, plot=plt)
plt.title('Normal Probabiliy Plot - Hover, Control')
plt.show()

exp_hover = [26.887, 36.138, 40.838, 5.789, 19.548, 20.499]

res = stats.probplot(exp_hover, plot=plt)
plt.title('Normal Probabiliy Plot - Hover, Experimental')
plt.show()

print(stats.ttest_ind(control_hover, exp_hover))

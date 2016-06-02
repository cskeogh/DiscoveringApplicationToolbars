from scipy import stats
import matplotlib.pyplot as plt

control_hover = [46.489, 15.818, 25.598, 78.423, 36.847, 23.947, 32.979 ]

res = stats.probplot(control_hover, plot=plt)
plt.title('Normal Probability Plot - Hover, Control')
plt.show()

exp_hover = [26.887, 36.138, 40.838, 5.789, 19.548, 20.499, 7.832]

res = stats.probplot(exp_hover, plot=plt)
plt.title('Normal Probability Plot - Hover, Experimental')
plt.show()

print(stats.ttest_ind(control_hover, exp_hover))

import datetime
import pprint

RAW_DATA_FOLDER = '..\\rawdata\\'

def analyse_total(lines, out):
    fieldsi = lines[0].split(', ')
    if (fieldsi[1].strip() != 'start'):
        raise RuntimeError('start missing')
    start_time = datetime.datetime.strptime(fieldsi[0], '%Y-%m-%d %H:%M:%S.%f')
    fieldsj = lines[len(lines)-1].split(', ')
    if (fieldsj[1].strip() != 'exit'):
        raise RuntimeError('exit missing')
    end_time = datetime.datetime.strptime(fieldsj[0], '%Y-%m-%d %H:%M:%S.%f')
    out.write('total runtime,%.5f\n' % (end_time-start_time).total_seconds())

def analyse_hover(lines, out):
    hoverdict = {}
    for i in range(0, len(lines)):
        fieldsi = lines[i].split(', ')
        if fieldsi[1].startswith('mouse enter'):
            button = fieldsi[1].split('mouse enter ')[1].strip()
            start_time = datetime.datetime.strptime(fieldsi[0], '%Y-%m-%d %H:%M:%S.%f')
            for j in range(i+1, len(lines)):
                fieldsj = lines[j].split(', ')
                if fieldsj[1].strip() == 'mouse exit':
                    end_time = datetime.datetime.strptime(fieldsj[0], '%Y-%m-%d %H:%M:%S.%f')
                    break
            existing = hoverdict.get(button)
            if existing:
                existing.append(end_time - start_time)
            else:
                hoverdict[button] = [end_time - start_time]

    out.write('hover totals\n')
    grandtotal = datetime.timedelta()
    for key in hoverdict.keys():
        out.write(key + '\n')
        sum = datetime.timedelta()
        for delta in hoverdict[key]:
            sum += delta
            out.write(',%.5f\n' % delta.total_seconds())
        out.write('total,%.5f\n' % sum.total_seconds())
        grandtotal += sum
    out.write('grandtotal,%.5f\n' % grandtotal.total_seconds())
    #pprint.pprint(hoverdict)

def analyse_tooltips(lines, out):
    tooltip_dict = {}
    for i in range(0, len(lines)):
        fieldsi = lines[i].split(', ')
        if fieldsi[1].startswith('show'):
            button = fieldsi[1].split('show ')[1].strip()
            start_time = datetime.datetime.strptime(fieldsi[0], '%Y-%m-%d %H:%M:%S.%f')
            for j in range(i+1, len(lines)):
                fieldsj = lines[j].split(', ')
                if fieldsj[1].startswith('hide'):
                    end_time = datetime.datetime.strptime(fieldsj[0], '%Y-%m-%d %H:%M:%S.%f')
                    break
            existing = tooltip_dict.get(button)
            if existing:
                existing.append(end_time - start_time)
            else:
                tooltip_dict[button] = [end_time - start_time]

    out.write('tooltip totals\n')
    grandtotal = datetime.timedelta()
    for key in tooltip_dict.keys():
        out.write(key + '\n')
        sum = datetime.timedelta();
        for delta in tooltip_dict[key]:
            sum += delta
            out.write(',%.5f\n' % delta.total_seconds())
        out.write('total,%.5f\n' % sum.total_seconds())
        grandtotal += sum
    out.write('grandtotal,%.5f\n' % grandtotal.total_seconds())
    #pprint.pprint(tooltip_dict)

def analyse_control(filename, out):
    out.write(filename + '\n')
    fp = open(filename, 'rt')
    lines = fp.readlines()
    fp.close()
    analyse_total(lines, out)
    analyse_hover(lines, out)
    analyse_tooltips(lines, out)

def analyse_experimental(filename, out):
    out.write(filename + '\n')
    fp = open(filename, 'rt')
    lines = fp.readlines()
    fp.close()
    analyse_total(lines, out)
    analyse_tooltips(lines, out)

def analyse():
    fo = open('trial_summary.csv', 'wt')
    analyse_control(RAW_DATA_FOLDER + 'ajf_ToolbarResearch_C201605201535.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'gyu_ToolbarResearch_E201605192006.txt', fo)
    analyse_control(RAW_DATA_FOLDER + 'hjf_ToolbarResearch_C201605191724.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'ijs_ToolbarResearch_E201605202058.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'lso_ToolbarResearch_E201605242057.txt', fo)
    analyse_control(RAW_DATA_FOLDER + 'mnr_ToolbarResearch_C201605212227.txt', fo)
    analyse_control(RAW_DATA_FOLDER + 'pgm_ToolbarResearch_C201605211448.txt', fo)
    analyse_control(RAW_DATA_FOLDER + 'poe_ToolbarResearch_C201605201434.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'sso_ToolbarResearch_E201605220815.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'zpd_ToolbarResearch_E201605201518.txt', fo)
    analyse_control(RAW_DATA_FOLDER + 'the_ToolbarResearch_C201605221408.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'xfx_ToolbarResearch_E201605221427.txt', fo)
    analyse_control(RAW_DATA_FOLDER + 'hel_ToolbarResearch_C201605221500.txt', fo)
    analyse_experimental(RAW_DATA_FOLDER + 'ihf_ToolbarResearch_E201605221514.txt', fo)
    fo.close()

if __name__ == '__main__':
    analyse()
